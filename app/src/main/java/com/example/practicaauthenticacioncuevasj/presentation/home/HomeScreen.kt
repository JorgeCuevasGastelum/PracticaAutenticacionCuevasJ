package com.example.practicaauthenticacioncuevasj.presentation.home

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.statusBarsPadding
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.State
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.compose.AsyncImage
import com.example.practicaauthenticacioncuevasj.presentation.model.Artist
import com.example.practicaauthenticacioncuevasj.ui.theme.Black
import com.google.firebase.auth.FirebaseAuth

@Composable
fun HomeScreen(
    auth: FirebaseAuth,
    viewmodel: HomeViewmodel = HomeViewmodel(),
    onLogout: () -> Unit
) {

    val artists: State<List<Artist>> = viewmodel.artist.collectAsState()

    Column(
        Modifier
            .fillMaxSize()
            .background(Black)
            .statusBarsPadding()
            .padding(top = 16.dp)
    ) {
        Text(
            "Popular artist",
            color = Color.White,
            fontWeight = FontWeight.Bold,
            fontSize = 30.sp,
            modifier = Modifier.padding(16.dp)
        )

        LazyRow {
            items(artists.value) {
                ArtistItem(it)
            }
        }

        Button(
            onClick = {
                auth.signOut()
                onLogout()
            },
            modifier = Modifier.padding(16.dp)
        ) {
            Text(text = "Cerrar sesión")
        }
    }
}

@Composable
fun ArtistItem(artist: Artist) {
    Column(horizontalAlignment = Alignment.CenterHorizontally) {
        AsyncImage(
            modifier = Modifier
                .size(60.dp)
                .clip(CircleShape),
            model = artist.image,
            contentDescription = "Artists image",
        )
        Spacer(modifier = Modifier.height(4.dp))
        Text(text = artist.name.orEmpty(), color = Color.White)
    }
}

@Preview
@Composable
fun ArtistItemPreview() {
    val artist = Artist(
        "Pepe",
        "El mejor",
        "https://cdn-images.dzcdn.net/images/cover/52ba3fac41f5a3e8fb58e45642d1666b/1900x1900-000000-81-0-0.jpg"
    )
    ArtistItem(artist = artist)
}
