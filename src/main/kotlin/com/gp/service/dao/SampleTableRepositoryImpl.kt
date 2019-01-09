package com.gp.service.dao

import org.hibernate.SessionFactory
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Repository
import javax.persistence.TypedQuery
import javax.transaction.Transactional

@Transactional(Transactional.TxType.MANDATORY)
@Repository
class SampleTableRepositoryImpl : SampleTableRepository {
    @Autowired
    lateinit var sessionFactory: SessionFactory

    override fun merge(sampleTable: SampleTable) {
        sessionFactory.currentSession.save(sampleTable)
    }

    override fun getById(id: Int): SampleTable {
        return sessionFactory.currentSession.get(SampleTable::class.java, id)
    }

    override fun getAll(): List<SampleTable> {
        val typedQuery =
            sessionFactory.currentSession.createQuery("from SampleTable") as TypedQuery<SampleTable>
        return typedQuery.resultList
    }
}