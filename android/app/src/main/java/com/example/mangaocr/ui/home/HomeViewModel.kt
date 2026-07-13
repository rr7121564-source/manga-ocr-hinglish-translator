package com.example.mangaocr.ui.home

import android.app.Application
import android.net.Uri
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.viewModelScope
import com.example.mangaocr.data.repository.MangaRepository
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

class HomeViewModel(application: Application) : AndroidViewModel(application) {

    private val repository = MangaRepository(application)

    private val _importProgress = MutableStateFlow(0)
    val importProgress: StateFlow<Int> = _importProgress

    private val _importStatus = MutableStateFlow("Ready to import")
    val importStatus: StateFlow<String> = _importStatus

    fun importZipFile(uri: Uri) {
        viewModelScope.launch {
            try {
                _importStatus.value = "Importing..."
                repository.importFromZip(uri) { progress ->
                    _importProgress.value = progress
                }
                _importStatus.value = "Import successful!"
            } catch (e: Exception) {
                _importStatus.value = "Error: ${e.message}"
            }
        }
    }
}
