package com.example.androidhomework.gitHub.net

import net.openid.appauth.ResponseTypeValues

object AuthConfig {

    const val AUTH_URI = "https://github.com/login/oauth/authorize"
    const val TOKEN_URI = "https://github.com/login/oauth/access_token"
    const val RESPONSE_TYPE = ResponseTypeValues.CODE
    const val SCOPE = "user,repo"

    const val CLIENT_ID = "3378a0c646b45d47935c"
    const val CLIENT_SECRET = "27174f24348a0696e2a7ee977f8c247459b8cae6"
    const val CALLBACK_URL = "skillbox://skillbox.ru/callback"
}