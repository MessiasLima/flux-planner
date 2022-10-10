package io.appoutlet.flux.desktop.feature.notes

import androidx.compose.runtime.Composable
import io.appoutlet.karavel.Page

class NotesPage : Page() {
    @Composable
    override fun content() {
        NotesView()
    }
}
