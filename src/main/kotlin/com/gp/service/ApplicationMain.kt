package com.gp.service

import org.springframework.context.annotation.AnnotationConfigApplicationContext

fun main(args: Array<String>) {
    val ctx = AnnotationConfigApplicationContext(ApplicationConfig::class.java)
    val applicationStart = ctx.getBean(ApplicationStart::class.java)
    applicationStart.start()
}
