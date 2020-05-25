package com.bridge.androidtechnicaltest

import com.bridge.androidtechnicaltest.db.Pupil
import org.junit.After
import org.junit.Assert
import org.junit.Before
import org.junit.Test
import org.mockito.Mock
import org.mockito.Mockito
import org.mockito.MockitoAnnotations


class PupilModelTest {
    private val pupilId = 1L
    private val name = "Testing Name"
    private val country = "Ireland"

    @Mock
    var pupilModel: Pupil? = null

    @Before
    @Throws(Exception::class)
    fun setUp() {
        MockitoAnnotations.initMocks(this)
        Mockito.`when`(pupilModel?.name).thenReturn(name)
        Mockito.`when`(pupilModel?.pupilId).thenReturn(pupilId)
        Mockito.`when`(pupilModel?.country).thenReturn(country)
    }

    @Test
    fun testNewName() {
        Mockito.`when`(pupilModel?.name).thenReturn(name)
        Assert.assertEquals("Testing Name", pupilModel?.name)
    }

    @Test
    fun testNewCounry() {
        Mockito.`when`(pupilModel?.country).thenReturn(country)
        Assert.assertEquals("Ireland", pupilModel?.country)
    }

    @Test
    fun testNewId() {
        Assert.assertEquals("Testing thumbsUp", pupilModel?.id)
    }


    @After
    @Throws(Exception::class)
    fun tearDown() {
        pupilModel = null
    }
}