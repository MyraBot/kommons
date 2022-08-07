package bot.myra.kommons

import org.slf4j.Logger
import org.slf4j.LoggerFactory
import kotlin.reflect.KClass

private val loggers = mutableMapOf<KClass<*>, Logger>()
private fun getLogger(klass: KClass<*>) = loggers.getOrPut(klass) { LoggerFactory.getLogger(klass.java) }

suspend fun kInfo(klass: KClass<*>, message: suspend (Unit) -> Any?) = getLogger(klass).info(message.invoke(Unit).toString())
fun info(klass: KClass<*>, message: (Unit) -> Any?) = getLogger(klass).info(message.invoke(Unit).toString())

suspend fun kError(klass: KClass<*>, message: suspend (Unit) -> Any?) = getLogger(klass).error(message.invoke(Unit).toString())
fun error(klass: KClass<*>, message: (Unit) -> Any?) = getLogger(klass).error(message.invoke(Unit).toString())

suspend fun kWarn(klass: KClass<*>, message: suspend (Unit) -> Any?) = getLogger(klass).warn(message.invoke(Unit).toString())
fun warn(klass: KClass<*>, message: (Unit) -> Any?) = getLogger(klass).warn(message.invoke(Unit).toString())

suspend fun kDebug(klass: KClass<*>, message: suspend (Unit) -> Any?) = getLogger(klass).debug(message.invoke(Unit).toString())
fun debug(klass: KClass<*>, message: (Unit) -> Any?) = getLogger(klass).debug(message.invoke(Unit).toString())

suspend fun kTrace(klass: KClass<*>, message: suspend (Unit) -> Any?) = getLogger(klass).trace(message.invoke(Unit).toString())
fun trace(klass: KClass<*>, message: (Unit) -> Any?) = getLogger(klass).trace(message.invoke(Unit).toString())