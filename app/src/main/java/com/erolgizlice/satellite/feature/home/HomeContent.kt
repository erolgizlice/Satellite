package com.erolgizlice.satellite.feature.home

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.material.MaterialTheme
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.input.TextFieldValue
import androidx.compose.ui.unit.dp
import com.erolgizlice.satellite.core.model.data.Satellite


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun HomeContent(
    satelliteList: List<Satellite>,
    onSatelliteClick: (Int) -> Unit,
    modifier: Modifier,
    searchState: SearchState = rememberSearchState()
) {
    LaunchedEffect(searchState.query.text) {
        searchState.searchResults =
            if (searchState.query.text.length > 2)
                satelliteList.filter {
                    it.name.contains(searchState.query.text, ignoreCase = true)
                }
            else satelliteList
    }

    Column(modifier = modifier) {
        SearchBar(
            modifier = Modifier
                .padding(16.dp),
            query = searchState.query,
            onQueryChange = { searchState.query = it },
        )
        ShowSatellites(
            modifier = modifier,
            satelliteList = searchState.searchResults,
            onSatelliteClick = onSatelliteClick
        )
    }
}

@Composable
fun ShowSatellites(
    modifier: Modifier,
    satelliteList: List<Satellite>,
    onSatelliteClick: (Int) -> Unit
) {
    LazyColumn(
        modifier = modifier
            .fillMaxSize()
            .padding(horizontal = 16.dp),
        contentPadding = PaddingValues(top = 8.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.spacedBy(8.dp)
    ) {
        satelliteList.forEachIndexed { index, satellite ->
            item(key = satellite.id) {
                SatelliteItem(
                    name = satellite.name,
                    isActive = satellite.active,
                    onSatelliteClick = { onSatelliteClick(satellite.id) }
                )
            }

            if (index != satelliteList.size - 1) {
                item {
                    Divider()
                }
            }
        }
    }
}

@Composable
private fun SearchBar(
    query: TextFieldValue,
    onQueryChange: (TextFieldValue) -> Unit,
    modifier: Modifier = Modifier
) {
    Surface(
        color = Color.LightGray,
        shape = RoundedCornerShape(38.dp),
        modifier = modifier
            .fillMaxWidth()
            .height(56.dp)
    ) {
        Box(Modifier.fillMaxSize()) {
            if (query.text.isEmpty()) {
                Row(
                    verticalAlignment = Alignment.CenterVertically,
                    modifier = Modifier
                        .fillMaxHeight()
                ) {
                    Spacer(Modifier.width(48.dp))
                    Text(
                        text = "Search",
                        style = MaterialTheme.typography.body1
                    )
                }
            }
            Row(
                verticalAlignment = Alignment.CenterVertically,
                modifier = Modifier
                    .fillMaxSize()
                    .wrapContentHeight()
            ) {
                IconButton(onClick = { /*TODO*/ }) {
                    Icon(
                        imageVector = Icons.Outlined.Search,
                        contentDescription = "search"
                    )
                }
                BasicTextField(
                    value = query,
                    onValueChange = onQueryChange,
                    modifier = Modifier
                        .weight(1f),
                    textStyle = MaterialTheme.typography.body1
                )
            }
        }
    }
}

@Composable
private fun rememberSearchState(
    query: TextFieldValue = TextFieldValue(""),
    searchResults: List<Satellite> = emptyList()
): SearchState {
    return remember {
        SearchState(
            query = query,
            searchResults = searchResults
        )
    }
}

@Stable
class SearchState(
    query: TextFieldValue,
    searchResults: List<Satellite>
) {
    var query by mutableStateOf(query)
    var searchResults by mutableStateOf(searchResults)
}