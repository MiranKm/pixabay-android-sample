@file:OptIn(ExperimentalCoroutinesApi::class)

package dev.miran.usecase

import com.google.common.truth.Truth.assertThat
import dev.miran.entity.HitsItem
import dev.miran.repository.ImageRepository
import io.mockk.MockKAnnotations
import io.mockk.coEvery
import io.mockk.coVerify
import io.mockk.impl.annotations.InjectMockKs
import io.mockk.impl.annotations.MockK
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.emptyFlow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.runBlocking
import kotlinx.coroutines.test.runTest
import org.junit.Before
import org.junit.Test

class HomeUseCaseTest {
    @MockK
    lateinit var imageRepository: ImageRepository

    @InjectMockKs
    lateinit var useCase: HomeUseCase

    @Before
    fun setUp() {
        MockKAnnotations.init(this, relaxUnitFun = true)
    }

    @Test
    fun `initial app launch request and its empty`() = runBlocking {
        coEvery { imageRepository.loadImages() } returns flowOfListHitsItem


        coEvery { imageRepository.loadImagesSize() } returns 0
        useCase.initialLoad(SEARCH_QUERY, null)
        val result = imageRepository.loadImages()
        assertThat(result).isNotEqualTo(emptyFlow<HitsItem>())
    }


    @Test
    fun `getting images by query`() = runTest {
        coEvery { imageRepository.getImageByQuery(any(), any()) } returns Unit
        useCase.getImageByQuery(SEARCH_QUERY, null)
        coVerify(exactly = 1) { imageRepository.getImageByQuery(SEARCH_QUERY, null) }
    }


    companion object {
        const val SEARCH_QUERY = "fruit"

        private val hitImageSample = HitsItem(
            id = 1122537,
            pageURL = "https://pixabay.com/photos/apple-water-droplets-fruit-moist-1122537/",
            type = "photo",
            tags = "apple, water droplets, fruit",
            previewURL = "https://cdn.pixabay.com/photo/2016/01/05/13/58/apple-1122537_150.jpg",
            previewWidth = 150,
            previewHeight = 95,
            webformatURL = "https://pixabay.com/get/gce3980a49333f35841990725fc36e94689aa25504fc6a8c48903ffa0ed30a363821fd7b80d60fc35af8bee17e2e0592600e360b9a702efda0ae13596efeb2df7_640.jpg",
            webformatWidth = 640,
            webformatHeight = 409,
            largeImageURL = "https://pixabay.com/get/g6a51469bc1de094b01120c6fad5252d0bf93738d01b26b7139e69674f36d791176f55429cebc756a5be998e74da50581334144ad75954031ae898c120101cc41_1280.jpg",
            imageWidth = 4752,
            imageHeight = 3044,
            imageSize = 5213632,
            views = 315025,
            downloads = 180751,
            collections = 1022,
            likes = 1127,
            comments = 184,
            userId = 1445608,
            user = "mploscar",
            userImageURL = "https://cdn.pixabay.com/user/2016/01/05/14-08-20-943_250x250.jpg"

        )

        val listOfHitsItem = listOf(hitImageSample)

        val flowOfListHitsItem = flow<List<HitsItem>> {}

    }
}