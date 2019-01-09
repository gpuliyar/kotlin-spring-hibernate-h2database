package com.gp.service

import com.gp.service.dao.SampleTable
import com.gp.service.dao.SampleTableRepository
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional

@Service
@Transactional
class ApplicationStart(private val sampleTableRepository: SampleTableRepository) {

    fun start() {
        val sampleTable1 = SampleTable(100, "abc", "test")
        val sampleTable2 = SampleTable(200, "def", "test")
        sampleTableRepository.merge(sampleTable1)
        sampleTableRepository.merge(sampleTable2)
        val results = sampleTableRepository.getAll()
        for (result in results)
            println("name : ${result.name}")
    }
}