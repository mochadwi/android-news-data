package io.mochadwi.mobilenews.util

import android.support.annotation.NonNull
import android.support.v7.widget.SearchView
import io.reactivex.Observable
import io.reactivex.subjects.BehaviorSubject


/**
 * Created by mochadwi on 3/15/18.
 */
class RxSearch {

    companion object {
        fun fromSearchView(@NonNull searchView: SearchView): Observable<String> {
            val subject = BehaviorSubject.create<String>()

            searchView.setOnQueryTextListener(object : SearchView.OnQueryTextListener {

                override fun onQueryTextSubmit(query: String): Boolean {
                    subject.onComplete()
                    return true
                }

                override fun onQueryTextChange(newText: String): Boolean {
                    if (!newText.isEmpty()) {
                        subject.onNext(newText)
                    }
                    return true
                }
            })

            return subject
        }

    }
}