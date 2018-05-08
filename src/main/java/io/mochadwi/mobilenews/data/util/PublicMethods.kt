package io.mochadwi.mobilenews.util;

import android.support.v4.app.Fragment
import android.support.v4.app.FragmentManager
import org.joda.time.format.ISODateTimeFormat
import java.util.*

/**
 * Created by mochadwi on 3/13/18.
 */

class PublicMethods {

    companion object {
        fun addFragmentToActivity(fm: FragmentManager,
                                  view: Fragment,
                                  layoutId: Int) {

            val transaction = fm.beginTransaction()
            transaction.add(layoutId, view)
            transaction.commit()
        }

        fun stringToLocalDate(dtStart: String?): Date {
            if (dtStart == null)
                return Date()

            val parser = ISODateTimeFormat.dateTimeParser()

            val localDate = parser.parseDateTime(dtStart).toLocalDateTime()

            return localDate.toDate()
        }
    }
}
