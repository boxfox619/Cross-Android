package com.linkbit.android.presenter

import android.widget.Toast
import com.google.firebase.auth.FirebaseAuth
import com.linkbit.android.ui.view.LoginView


class LoginPresenter : BasePresenter<LoginView>{

    lateinit var loginView: LoginView


    fun login(){
        loginView.let {
            it.getFirebaseAuth().signin
            if(it.getEmail().isEmpty() || it.getPassword().isEmpty()){
                //error
            }else{
                it.getFirebaseAuth().signInWithEmailAndPassword(it.getEmail(),it.getPassword())
                        .addOnCompleteListener { auth ->
                            if(auth.isSuccessful){
                                Toast.makeText(it.getContext(),"로그인 성공",Toast.LENGTH_SHORT).show()
                            }else{
                                Toast.makeText(it.getContext(),"로그인 실패",Toast.LENGTH_SHORT).show()
                            }
                        }
            }
        }
    }

    fun onAuthState() : FirebaseAuth.AuthStateListener{
        return FirebaseAuth.AuthStateListener {
            firebaseAuth->firebaseAuth?.let {
                //login state check
            }
        }
    }

    override fun addView(view: LoginView) {
        this.loginView=view
    }

    override fun removeView() {
        this.loginView==null
    }
}

