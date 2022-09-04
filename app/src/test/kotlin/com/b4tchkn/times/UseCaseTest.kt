package com.b4tchkn.times

import org.junit.After
import org.junit.Before
import org.mockito.MockitoAnnotations

open class UseCaseTest {
    private lateinit var closeable: AutoCloseable

    @Before
    fun openMocks() {
        closeable = MockitoAnnotations.openMocks(this)
    }

    @After
    fun releaseMocks() {
        closeable.close()
    }
}
