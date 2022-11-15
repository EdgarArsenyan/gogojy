package di

import com.example.data.data.apiRepo.Api
import com.example.data.data.apiRepo.ApiRepoImpl
import com.example.data.data.util.URLConstants
import domain.repo.ApiRepository
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import org.koin.dsl.module
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

val dataModule = module {

        single<ApiRepository> {
            ApiRepoImpl(api = get())
        }

        single<Api> {
            Retrofit.Builder()
                .addConverterFactory(GsonConverterFactory.create())
                .client(get())
                .baseUrl(URLConstants.BASE_URL)
                .build().create(Api::class.java)
        }

    }

    val serviceModule =
        module {
            single<Api> {
                Retrofit.Builder()
                    .addConverterFactory(GsonConverterFactory.create())
                    .client(get())
                    .baseUrl(URLConstants.BASE_URL)
                    .build().create(Api::class.java)
            }
        }

    val okHttpModule =
        module {
            single {
                val logging = HttpLoggingInterceptor()
                logging.level = HttpLoggingInterceptor.Level.BODY
                OkHttpClient.Builder()
                    .addInterceptor(logging)
                    .build()
            }
        }

