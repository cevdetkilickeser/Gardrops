package com.cevdetkilickeser.gardrops.data.repository

interface AuthRepository {
    suspend fun checkSignUpFields(username: String, email: String, password: String, isUserAgreementChecked: Boolean): Pair<Boolean, String>
    suspend fun checkUsernameAvailability(username: String): Pair<Boolean, String>
    suspend fun signUpWithUsernameOrEmail(username: String, email: String, password: String, isUserAgreementChecked: Boolean): Pair<Boolean, String>
    suspend fun createUserWithEmailAndPassword(username: String, email: String, password: String): Pair<Boolean, String>
    suspend fun addUsernameToFirestore(username: String, email: String)
    suspend fun checkSignInFields(emailOrUsername: String): Pair<Boolean, String>
    suspend fun signInWithEmailOrUsername(emailOrUsername: String, password: String): Pair<Boolean, String>
    suspend fun signInWithEmailAndPassword(email: String, password: String): Pair<Boolean, String>
}