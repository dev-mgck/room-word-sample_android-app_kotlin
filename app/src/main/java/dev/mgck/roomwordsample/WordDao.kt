package dev.mgck.roomwordsample

// STEP 2

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import kotlinx.coroutines.flow.Flow

/*In the DAO (data access object), you specify SQL queries and associate them with
method calls. The compiler checks the SQL and generates queries from convenience
annotations for common queries, such as @Insert. Room uses the DAO to create a clean
API for your code.

The DAO must be an interface or abstract class.

By default, all queries must be executed on a separate thread.*/

// The @Dao annotation identifies it as a DAO class for Room.
@Dao
interface WordDao {

    // The flow always holds/caches latest version of data. Notifies its observers when the
    // data has changed.
    @Query("SELECT * FROM word_table ORDER BY word ASC")
    fun getAlphabetizedWords(): Flow<List<Word>>

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun insert(word: Word)

    @Query("DELETE FROM word_table")
    suspend fun deleteAll()

    // suspend fun insert(word: Word) : Declares a suspend function to insert one word.

    /* The @Insert annotation is a special DAO method annotation where you don't have
    to provide any SQL! (There are also @Delete and @Update annotations for deleting and
    updating rows, but you are not using them in this app.) */

    /* onConflict = OnConflictStrategy.IGNORE: The selected onConflict strategy
    ignores a new word if it's exactly the same as one already in the list. */

    // suspend fun deleteAll(): Declares a suspend function to delete all the words.

    /* There is no convenience annotation for deleting multiple entities, so it's annotated
    with the generic @Query. */

    /* @Query("DELETE FROM word_table"): @Query requires that you provide a SQL query
    as a string parameter to the annotation, allowing for complex read queries and other
    operations. */

    /* fun getAlphabetizedWords(): List<Word>: A method to get all the words and have
    it return a List of Words. */

    /* @Query("SELECT * FROM word_table ORDER BY word ASC"): Query that returns a
    list of words sorted in ascending order. */

    /* A Flow is an async sequence of values

     Flow produces values one at a time (instead of all at once) that can generate
     values from async operations like network requests, database calls, or other
     async code. It supports coroutines throughout its API, so you can transform
     a flow using coroutines as well! */

}