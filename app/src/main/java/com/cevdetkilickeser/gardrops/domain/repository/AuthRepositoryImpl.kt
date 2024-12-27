package com.cevdetkilickeser.gardrops.domain.repository

import com.cevdetkilickeser.gardrops.data.repository.AuthRepository
import com.cevdetkilickeser.gardrops.regex
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.FirebaseFirestore
import kotlinx.coroutines.tasks.await
import javax.inject.Inject

class AuthRepositoryImpl @Inject constructor(
    private val auth: FirebaseAuth, private val firestore: FirebaseFirestore
) : AuthRepository {

    override suspend fun signUpWithUsernameOrEmail(
        username: String,
        email: String,
        password: String,
        isUserAgreementChecked: Boolean
    ): Pair<Boolean, String> {
        val checkFieldsResult = checkFields(username, email, password, isUserAgreementChecked)
        return if (!checkFieldsResult.first) {
            Pair(false, checkFieldsResult.second)
        } else {
            val checkUsernameAvailabilityResult = checkUsernameAvailability(username)
            return if (!checkUsernameAvailabilityResult.first) {
                Pair(false, checkUsernameAvailabilityResult.second)
            } else {
                val createUserWithEmailAndPasswordResult =
                    createUserWithEmailAndPassword(username, email, password)
                return if (!createUserWithEmailAndPasswordResult.first) {
                    Pair(
                        false, createUserWithEmailAndPasswordResult.second
                    )
                } else {
                    addUsernameToFirestore(username, email)
                    Pair(true, "")
                }
            }
        }
    }

    override suspend fun checkFields(
        username: String, email: String, password: String, isUserAgreementChecked: Boolean
    ): Pair<Boolean, String> {
        return if (!isUserAgreementChecked) {
            Pair(false, "Kullanıcı sözleşmesini okuyup, kabul etmeniz gerekiyor")
        } else if (username.isEmpty()) {
            Pair(false, "Bir kullanıcı adı belirleyin")
        } else if (username.length < 3) {
            Pair(false, "Kullanıcı adı çok kısa görünüyor")
        } else if (!username.matches(regex)) {
            Pair(false, "Kullanıcı adı Türkçe ve özel karakter içeremez")
        } else if (password.isEmpty()) {
            Pair(false, "Şifreniz en az 6 karakterden oluşmalıdır")
        } else if (email.isEmpty()) {
            Pair(false, "E-posta adresi boş bırakılamaz")
        } else {
            Pair(true, "")
        }
    }

    override suspend fun checkUsernameAvailability(username: String): Pair<Boolean, String> {
        return try {
            val querySnapshot =
                firestore.collection("gardropsUsernameTable")
                    .whereEqualTo("username", username)
                    .get()
                    .await()
            return if (querySnapshot.isEmpty) {
                Pair(true, "")
            } else {
                Pair(false, "Üzgünüz $username kullanımda")
            }
        } catch (_: Exception) {
            Pair(false, "Bir hata oluştu")
        }
    }

    override suspend fun createUserWithEmailAndPassword(
        username: String,
        email: String,
        password: String
    ): Pair<Boolean, String> {
        return try {
            auth.createUserWithEmailAndPassword(email, password).await()
            Pair(true, "Kullanıcı başarıyla oluşturuldu")
        } catch (e: Exception) {
            Pair(false, "Üzgünüz bu e-posta adresi kullanımda")
        }
    }

    override suspend fun addUsernameToFirestore(username: String, email: String) {
        val data = hashMapOf(
            "username" to username, "email" to email
        )
        try {
            firestore.collection("gardropsUsernameTable").add(data).await()
        } catch (e: Exception) {
            e.printStackTrace()
            throw Exception("Failed to add username to Firestore: ${e.message}")
        }
    }
}