package com.gp.service.dao

import javax.persistence.*

@Entity
@Table(name = "MS_GRC_AREA_OF_COMPLIA")
data class SampleTable (
    @Id
    @Column(name="ID")
    val id: Int,

    @Column(name="NAME", nullable = false, length = 1000)
    val name: String,

    @Column(name="DESC", nullable = true, length = 4000)
    val desc: String? = null
)