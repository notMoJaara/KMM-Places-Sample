package com.mohamad.kmmplaces.functional

import com.mohamad.kmmplaces.util.Either
import com.mohamad.kmmplaces.util.Either.Right
import com.mohamad.kmmplaces.util.Either.Left
import kotlin.contracts.ExperimentalContracts
import kotlin.contracts.contract


/**
 * Creates a Left type.
 * @see Left
 */
internal inline fun <L, Nothing> Either<L, Nothing>.left(a: L) = Left(a)

/**
 * Creates a right type.
 * @see Right
 */
internal inline fun <Nothing, R> Either<Nothing, R>.right(b: R) = Right(b)


/**
 * Applies fnL if this is a Left or fnR if this is a Right.
 * @see Left
 * @see Right
 */
internal inline fun <L, R, T> Either<L, R>.fold(fnL: (L) -> T, fnR: (R) -> T): T = performFold(fnL, fnR)!!

/**
 * Applies fnL if this is a Left or fnR if this is a Right.
 * @see Left
 * @see Right
 */
internal inline fun <L, R, T> Either<L, R>.nullableFold(fnL: (L) -> T?, fnR: (R) -> T?): T? = performFold(fnL, fnR)

internal inline fun <L, R, T> Either<L, R>.performFold(fnL: (L) -> T?, fnR: (R) -> T?): T? =
    when (this) {
        is Left -> fnL(value)
        is Right -> fnR(value)
    }


/**
 * Returns true if this is a Right, false otherwise.
 * @see Right
 */
@OptIn(ExperimentalContracts::class)
internal inline fun <L, R> Either<L, R>.isRight(): Boolean {
    contract {
        returns(true) implies (this@isRight is Right<R>)
        returns(false) implies (this@isRight is Left<L>)
    }
    return this is Either.Right<R>
}

/**
 * Returns true if this is a Left, false otherwise.
 * @see Left
 */
@OptIn(ExperimentalContracts::class)
internal inline fun <L, R> Either<L, R>.isLeft(): Boolean {
    contract {
        returns(true) implies (this@isLeft is Left<L>)
        returns(false) implies (this@isLeft is Right<R>)
    }
    return this is Left<L>
}

/**
 * Composes 2 functions
 * See <a href="https://proandroiddev.com/kotlins-nothing-type-946de7d464fb">Credits to Alex Hart.</a>
 */
internal inline fun <B, C, A> ((A) -> B).c(crossinline f: (B) -> C): (A) -> C = {
    f(this(it))
}

/**
 * Right-biased flatMap() FP convention which means that Right is assumed to be the default case
 * to operate on. If it is Left, operations like map, flatMap, ... return the Left value unchanged.
 */
internal inline fun <L, R, T> Either<L, R>.flatMap(fn: (R) -> Either<L, T>): Either<L, T> =
    when (this) {
        is Left -> Left(value)
        is Right -> fn(value)
    }

/**
 * Left-biased onFailure() FP convention dictates that when this class is Left, it'll perform
 * the onFailure functionality passed as a parameter, but, overall will still return an either
 * object so you chain calls.
 */
internal inline fun <L, R> Either<L, R>.onFailure(fn: (failure: L) -> Unit): Either<L, R> =
    this.apply { if (this is Left) fn(value) }

/**
 * Right-biased onSuccess() FP convention dictates that when this class is Right, it'll perform
 * the onSuccess functionality passed as a parameter, but, overall will still return an either
 * object so you chain calls.
 */
internal inline fun <L, R> Either<L, R>.onSuccess(fn: (success: R) -> Unit): Either<L, R> =
    this.apply { if (this is Right) fn(value) }

/**
 * Right-biased map() FP convention which means that Right is assumed to be the default case
 * to operate on. If it is Left, operations like map, flatMap, ... return the Left value unchanged.
 */
internal inline fun <L, R, T> Either<L, R>.map(fn: (R) -> (T)): Either<L, T> =
    when (this) {
        is Left -> Left(value)
        is Right -> Right(fn(value))
    }

/**
 * Left-biased map() FP convention which means that Right is assumed to be the default case
 * to operate on. If it is Right, operations like map, flatMap, ... return the Right value unchanged.
 */
internal inline fun <T, L, R> Either<L, R>.mapLeft(fn: (L) -> (T)): Either<T, R> =
    when (this) {
        is Left -> Left(fn(value))
        is Right -> Right(value)
    }

/**
 * Returns the value from this `Right` or the given argument if this is a `Left`.
 * Right(12).getOrElse(17) RETURNS 12 and Left(12).getOrElse(17) RETURNS 17
 */
internal inline fun <L, R> Either<L, R>.getOrElse(value: R): R =
    when (this) {
        is Left -> value
        is Right -> this.value
    }

/**
 * Folds a list into an Either while it doesn't go Left.
 * Allows for accumulation of value through iterations.
 * @return the final accumulated value if there are NO Left results, or the first Left result otherwise.
 */
internal inline fun <T, L, R> Iterable<T>.foldToEitherWhileRight(initialValue: R, crossinline fn: (item: T, accumulated: R) -> Either<L, R>): Either<L, R> {
    return this.fold<T, Either<L, R>>(Right(initialValue)) { acc, item ->
        acc.flatMap { accumulatedValue -> fn(item, accumulatedValue) }
    }
}
