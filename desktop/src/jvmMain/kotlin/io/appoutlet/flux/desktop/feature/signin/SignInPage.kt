package io.appoutlet.flux.desktop.feature.signin

import androidx.compose.runtime.Composable
import io.appoutlet.flux.desktop.feature.notes.NotesPage
import io.appoutlet.karavel.Karavel
import io.appoutlet.karavel.Page

class SignInPage(private val mainKaravel: Karavel?) : Page() {
    @Composable
    override fun content() {
        SignInView(karavel) {
            mainKaravel?.navigate(NotesPage())
        }
    }
}
