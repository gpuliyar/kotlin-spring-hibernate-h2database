package com.gp.service.dao

interface SampleTableRepository {
    fun merge(sampleTable: SampleTable)

    fun getById(id: Int): SampleTable

    fun getAll(): List<SampleTable>
}