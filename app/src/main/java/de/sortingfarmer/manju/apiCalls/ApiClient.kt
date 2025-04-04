package de.sortingfarmer.manju.apiCalls

import de.sortingfarmer.manju.apiCalls.api.AtHome
import de.sortingfarmer.manju.apiCalls.api.Authentication
import de.sortingfarmer.manju.apiCalls.api.Authenticator
import de.sortingfarmer.manju.apiCalls.api.Author
import de.sortingfarmer.manju.apiCalls.api.Captcha
import de.sortingfarmer.manju.apiCalls.api.Chapter
import de.sortingfarmer.manju.apiCalls.api.Cover
import de.sortingfarmer.manju.apiCalls.api.CustomList
import de.sortingfarmer.manju.apiCalls.api.Feed
import de.sortingfarmer.manju.apiCalls.api.Follows
import de.sortingfarmer.manju.apiCalls.api.Forums
import de.sortingfarmer.manju.apiCalls.api.Infrastructure
import de.sortingfarmer.manju.apiCalls.api.Legacy
import de.sortingfarmer.manju.apiCalls.api.Manga
import de.sortingfarmer.manju.apiCalls.api.Rating
import de.sortingfarmer.manju.apiCalls.api.ReadMarker
import de.sortingfarmer.manju.apiCalls.api.Report
import de.sortingfarmer.manju.apiCalls.api.ScanlationGroup
import de.sortingfarmer.manju.apiCalls.api.Statistics
import de.sortingfarmer.manju.apiCalls.api.Upload
import de.sortingfarmer.manju.apiCalls.api.User
import de.sortingfarmer.manju.dataClass.Settings
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import kotlin.getValue

object ApiClient {
    const val BASE_URL = "https://api.mangadex.org"
    const val IMAGE_URL = "https://uploads.mangadex.org"
    const val AUTH_URL = "https://auth.mangadex.org"

    private val retrofit: Retrofit by lazy {
        Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
    }

    private val authRetrofit: Retrofit by lazy {
        Retrofit.Builder()
            .baseUrl(AUTH_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
    }

    @Deprecated("Use category specific api calls instead")
    val api: Api by lazy {
        retrofit.create(Api::class.java)
    }

    val apiClient: ApiClient by lazy {
        retrofit.create(ApiClient::class.java)
    }

    val atHome: AtHome by lazy {
        retrofit.create(AtHome::class.java)
    }

    val authentication: Authentication by lazy {
        authRetrofit.create(Authentication::class.java)
    }

    val authenticator: Authenticator by lazy {
        retrofit.create(Authenticator::class.java)
    }

    val author: Author by lazy {
        retrofit.create(Author::class.java)
    }

    val captcha: Captcha by lazy {
        retrofit.create(Captcha::class.java)
    }

    val chapter: Chapter by lazy {
        retrofit.create(Chapter::class.java)
    }

    val cover: Cover by lazy {
        retrofit.create(Cover::class.java)
    }

    val customList: CustomList by lazy {
        retrofit.create(CustomList::class.java)
    }

    val feed: Feed by lazy {
        retrofit.create(Feed::class.java)
    }

    val follows: Follows by lazy {
        retrofit.create(Follows::class.java)
    }

    val forums: Forums by lazy {
        retrofit.create(Forums::class.java)
    }

    val infrastructure: Infrastructure by lazy {
        retrofit.create(Infrastructure::class.java)
    }

    val legacy: Legacy by lazy {
        retrofit.create(Legacy::class.java)
    }

    val manga: Manga by lazy {
        retrofit.create(Manga::class.java)
    }

    val rating: Rating by lazy {
        retrofit.create(Rating::class.java)
    }

    val readMarker: ReadMarker by lazy {
        retrofit.create(ReadMarker::class.java)
    }

    val report: Report by lazy {
        retrofit.create(Report::class.java)
    }

    val scanlationGroup: ScanlationGroup by lazy {
        retrofit.create(ScanlationGroup::class.java)
    }

    val settings: Settings by lazy {
        retrofit.create(Settings::class.java)
    }

    val statistics: Statistics by lazy {
        retrofit.create(Statistics::class.java)
    }

    val upload: Upload by lazy {
        retrofit.create(Upload::class.java)
    }

    val user: User by lazy {
        retrofit.create(User::class.java)
    }
}