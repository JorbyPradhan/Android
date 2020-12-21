package com.example.cleanarchitecturemvvm.presentation.ui.auth.data

import com.example.cleanarchitecturemvvm.presentation.ui.auth.data.model.LoggedInUser
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseUser
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay
import kotlinx.coroutines.withContext
import java.io.IOException

/**
 * Class that handles authentication w/ login credentials and retrieves user information.
 */
class LoginDataSource {

    private var mAuth: FirebaseAuth = FirebaseAuth.getInstance()
    private lateinit var userInfo: LoggedInUser
    suspend fun login(username: String, password: String): Result<LoggedInUser> {
        return try {
            // TODO: handle loggedInUser authentication
            withContext(Dispatchers.IO) {
                loginProgress(username, password)
                delay(3000)
                if (userInfo.userId == "") {
                    Result.Fail(userInfo)
                } else
                    Result.Success(userInfo)
            }
            // val fakeUser = LoggedInUser(java.util.UUID.randomUUID().toString(), "Jane Doe")
        } catch (e: Throwable) {
            Result.Error(IOException("Error logging in", e))
        }
    }

    private fun loginProgress(username: String, password: String) {
        mAuth.signInWithEmailAndPassword(username, password)
            .addOnCompleteListener { task ->
                if (task.isSuccessful) {
                    // Sign in success, update UI with the signed-in user's information
                    val user = mAuth.currentUser
                    user?.let { userInfo = LoggedInUser(user.uid, user.email!!) }
                } else {
                    userInfo = LoggedInUser("", "")
                }
            }
    }

    /* fun updateUI(currentUser: FirebaseUser?):LoggedInUser{
        return if(currentUser != null){
            if(currentUser.isEmailVerified){
                LoggedInUser(currentUser.uid, currentUser.email!!)
            }else{
                LoggedInUser("","")
            }
        } else{
            LoggedInUser("","")
        }
    }*/
    fun logout() {
        mAuth.signOut()
        // TODO: revoke authentication
    }
}