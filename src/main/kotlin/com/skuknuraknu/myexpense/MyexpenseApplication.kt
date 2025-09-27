package com.skuknuraknu.myexpense

import org.slf4j.LoggerFactory
import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication

@SpringBootApplication
class MyexpenseApplication

private val log = LoggerFactory.getLogger(MyexpenseApplication::class.java)
fun main(args: Array<String>) {
	runApplication<MyexpenseApplication>(*args)
}

	