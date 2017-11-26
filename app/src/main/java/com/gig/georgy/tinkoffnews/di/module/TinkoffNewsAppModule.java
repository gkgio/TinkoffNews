package com.gig.georgy.tinkoffnews.di.module;

import android.app.Application;

import com.gig.georgy.tinkoffnews.BuildConfig;
import com.gig.georgy.tinkoffnews.app.TinkoffNewsApp;

import com.gig.georgy.tinkoffnews.common.Config;
import com.gig.georgy.tinkoffnews.common.LongWrapper;
import com.gig.georgy.tinkoffnews.common.eventbus.Bus;
import com.gig.georgy.tinkoffnews.network.NetworkService;
import com.google.gson.ExclusionStrategy;
import com.google.gson.FieldAttributes;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.TypeAdapter;
import com.google.gson.reflect.TypeToken;
import com.google.gson.stream.JsonReader;
import com.google.gson.stream.JsonWriter;

import java.io.IOException;
import javax.inject.Named;
import javax.inject.Singleton;
import java.lang.reflect.Type;

import dagger.Module;
import dagger.Provides;
import io.realm.RealmList;
import io.realm.RealmObject;
import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by georgy on 25.11.2017.
 * GIG
 */

@Module
public class TinkoffNewsAppModule {

    private final TinkoffNewsApp app;


    public TinkoffNewsAppModule(TinkoffNewsApp app) {
        this.app = app;
    }

    @Provides
    @Singleton
    Application provideApplication() {
        return app;
    }

    @Provides
    @Singleton
    Bus provideEventBus() {
        return new Bus();
    }

    @Provides
    @Singleton
    Gson provideGson() {
        Type token = new TypeToken<RealmList<LongWrapper>>() {
        }.getType();
        return new GsonBuilder()
                .setExclusionStrategies(new ExclusionStrategy() {
                    @Override
                    public boolean shouldSkipField(FieldAttributes f) {
                        return f.getDeclaringClass().equals(RealmObject.class);
                    }

                    @Override
                    public boolean shouldSkipClass(Class<?> clazz) {
                        return false;
                    }
                })
                .registerTypeAdapter(token, new TypeAdapter<RealmList<LongWrapper>>() {

                    @Override
                    public void write(JsonWriter out, RealmList<LongWrapper> value) throws IOException {
                        out.beginArray();
                        for (LongWrapper longWrapper : value) {
                            out.value(longWrapper.getValue());
                        }
                        out.endArray();
                    }

                    @Override
                    public RealmList<LongWrapper> read(JsonReader in) throws IOException {
                        RealmList<LongWrapper> list = new RealmList<>();

                        in.beginArray();
                        while (in.hasNext()) {
                            list.add(new LongWrapper(in.nextLong()));
                        }
                        in.endArray();
                        return list;
                    }
                })
                .create();
    }

    @Provides
    @Singleton
    OkHttpClient provideHttpClient() {
        OkHttpClient.Builder builder = new OkHttpClient.Builder();
        HttpLoggingInterceptor interceptor = new HttpLoggingInterceptor();
        interceptor.setLevel(BuildConfig.DEBUG ? HttpLoggingInterceptor.Level.BODY : HttpLoggingInterceptor.Level.NONE);
        builder.interceptors().add(interceptor);
        return builder.build();
    }

    @Provides
    @Named("no_cached")
    @Singleton
    NetworkService provideService(Gson gson, OkHttpClient client) {

        Retrofit retrofit = new Retrofit.Builder()
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .addConverterFactory(GsonConverterFactory.create(gson))
                .baseUrl(Config.API_URL)
                .client(client)
                .build();

        return retrofit.create(NetworkService.class);
    }
}
