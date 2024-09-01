package site.shug.demo.basic

import org.junit.jupiter.api.Assertions
import kotlin.properties.Delegates
import kotlin.reflect.KProperty
import kotlin.test.Test

/**
 * 声明一个空的类
 */
class Empty

/**
 * 声明含有x和y字段的类
 * Point是类名
 * val x: Int, val y: Int称为类头
 * 默认情况下会使用类头自动创建构造函数
 */
open class Point(val x: Int, val y: Int) {
    val label = "Label" // 在类里面定义对象的字段
    // 可以复写字段
    open var point = 1

    // 定义属性访问器
    var custom: Int
        get() = 1
        set(value) {}

    var cc = 1
    set(value) {
        // field指定cc
        field = value
    }

    // 伴生对象, 可以在里面定义类的的静态属性或方法
    companion object {
        val Origin = Point(0, 0)

        fun createPoint(x: Int, y: Int): Point {
            return Point(x, y)
        }
    }

    // 让子类可以重写show函数
    open fun show() {}
}

/**
 * 没有用val或var声明, 在对象创建后, 不能访问到x和y
 */
class Point1(x: Int, y: Int)

/**
 * Kotlin 中的类有一个主构造函数，可能还有一个或多个辅助构造函数。
 * 主构造函数在类头中声明，位于类名和可选类型参数之后。
 * 如果主构造函数没有任何注释或可见性修饰符，则可以省略constructor函数关键字：class Point2(val x: Int, val y: Int)
 * 有注解时不能省略constructor
 * class Customer public @Inject constructor(name: String)
 */
class Point2 constructor(val x: Int, val y: Int) {
    init {
        // 类头不能包含任何可运行的代码。所以主构造函数好像不能插入代码,
        // 为解决这个问题, 引入init块, init块里面的代码将插入到主构造函数
    }
    // 次构造函数, 会调用主构造函数, 如果主构造函数没有参数, 也会隐式调用构造函数
    constructor(x: Int, y: Int, log: String) : this(x, y) {
    }
}

/**
 * 接口可以包含抽象方法的声明以及方法实现。
 */
interface Show {
    fun show() {
        // 默认实现
    }
}

/**
 * 功能 (SAM) 接口
 */
fun interface Func {
    fun func(a: Int): Int
}

class Point3D(x: Int, y: Int, val z: Int): Point(x, y), Show  { // 继承于Point, 并实现了Show接口
    override var point = 3
    // 重写父类函数
    override fun show() {
        // 子类中可以使用super访问父类, 并且还可以通过<>指定访问父类或接口的实现
        super<Show>.show()
    }

    /**
     * lateinit延长初始化
     *
     * 不在主构造函数中，
     * 并且仅当该属性没有自定义 getter 或 setter 时），
     * 以及顶级属性和局部变量。
     * 属性或变量的类型必须是不可为 null 的，
     * 并且不能是基本类型。
     */
    lateinit var lateVar: String

    fun testLate() {
        // 检测lateVar是否初始化
        Assertions.assertFalse(this::lateVar.isInitialized)
        // 初始化lateVar
        this.lateVar = "LateVar"
        // 已经初始化
        Assertions.assertTrue(this::lateVar.isInitialized)
    }
}

/**
 * kotlin的类如果没有显示继承, 默认都是继承Any,
 * 默认情况下, kotlin的类是不能继承的, open标注的类才能继承
 * public open class Any {
 *     public open operator fun equals(other: Any?): Boolean //判断对象相等
 *     public open fun hashCode(): Int // 计算对象的Hash
 *     public open fun toString(): String // 将对象转换成字符串
 * }
 *
 */
const val constVal = "is const"

/**
 * 密封类本身始终是抽象类，因此无法直接实例化。
 * 但是，它可以包含或继承构造函数。
 * 这些构造函数不是用于创建密封类本身的实例，而是用于其子类。
 */
sealed class Error(val message: String) {
    class NetworkError : Error("Network failure")
    class DatabaseError : Error("Database cannot be reached")
    class UnknownError : Error("An unknown error has occurred")
}

// T是协变类型参数, 只能生成T, Source<Int>可以赋值给Source<Number>
fun interface Source<out T> {
    fun nextT(): T
}

// T是逆变型参数, 只能消耗T,  Source<Number>可以赋值给Source<Int>
fun interface Comparable<in T> {
    operator fun compareTo(other: T): Int
}

class OutClass {
    val out = 1
    inner class InnerClass {
        val accessOut: Int
        get() = out
    }
}

enum class Direction {
    NORTH, SOUTH, WEST, EAST
}


/**
 * 有时，将值包装在类中以创建更特定于域的类型很有用。
 * 然而，由于额外的堆分配，它引入了运行时开销。
 * 此外，如果包装类型是原始类型，则性能损失会很大，因为原始类型通常由运行时进行大量优化，而它们的包装器不会得到任何特殊处理。
 */
@JvmInline
value class Password(private val s: String)

object Singleton {
    val label = "Singleton"
}

/**
 * 对象代理
 */

// 代理接口
interface Base {
    fun printA(): String
    fun printB(): String
    fun printC(): String
}

// 具体实现类
class BaseAImpl : Base {
    override fun printA() = "BaseAImpl"
    override fun printB() = "BaseAImpl"
    override fun printC() = "BaseAImpl"
}

// BaseAImpl才是具体实现
class BaseBImpl : Base by BaseAImpl() {
    // 实现printA, 不代理printA方法
    override fun printA() = "BaseBImpl"
}

// 传入的base才是具体实现
class BaseCImpl(base: Base) : Base by base {
    override fun printA() = "BaseCImpl"
}

/**
 * 委托属性
 */
class PropertyDelegate {
    // 读取数据将调用getValue
    operator fun getValue(thisRef: Any?, property: KProperty<*>): String {
        return "delegating ${property.name}"
    }
    // 写入数据将调用setValue
    operator fun setValue(thisRef: Any?, property: KProperty<*>, value: String) {
    }
}

class Delegate {

    var property: String by PropertyDelegate()

    // 第一次调用get()会执行传递给lazy()的 lambda 并记住结果。
    val lazyValue: String by lazy {
        "lazyValue"
    }

    // 监听修改
    var observable: String by Delegates.observable("observable") { property, oldValue, newValue ->
        println("$property -> $oldValue -> $newValue")
    }

    var vetoable: String by Delegates.vetoable("vetoable") { property, oldValue, newValue ->
        // 返回true才会保存新值
        false
    }

    // 委托给其他属性
    var newPosition = 0
    var oldPosition by this::newPosition

}

// DelegateAlias和Delegate是一样的, 类型别名不会引入新类型
typealias DelegateAlias = Delegate

class ClassesTest {
    /**
     * 访问对象属性
     */
    @Test
    fun testAccessProperties() {
        val point = Point(1, 2)
        Assertions.assertEquals(point.x, 1)
        Assertions.assertEquals(point.y, 2)
    }

    /**
     * 访问对象属性
     */
    @Test
    fun testCompanionObject() {
        val point = Point.Origin
        Assertions.assertEquals(point.x, 0)
        Assertions.assertEquals(point.y, 0)

        val point1 = Point.createPoint(1, 1)
        Assertions.assertEquals(point1.x, 1)
        Assertions.assertEquals(point1.y, 1)
    }

    /**
     * const编译时常量
     * 必须是顶级属性，或者是object声明或伴生对象的成员。
     * 必须使用String类型或原始类型的值进行初始化
     * 不能是自定义 getter
     */
    @Test
    fun testConst() {
        Assertions.assertEquals(constVal, "is const")
    }

    /**
     * 测试延迟初始化
     */
    @Test
    fun tesLateinit() {
        val point = Point3D(1, 2, 3)
        point.testLate()
    }

    /**
     * 测试功能 (SAM) 接口
     * 仅具有一个抽象成员函数的接口称为函数式接口或单一抽象方法（SAM）接口。
     * 函数式接口可以有多个非抽象成员函数，但只能有一个抽象成员函数。
     */
    @Test
    fun testSAM() {
        val func: Func = Func { i: Int -> i }
        Assertions.assertEquals(func.func(1), 1)
    }

    /**
     * 类、对象、接口、构造函数和函数以及属性及其设置器都可以具有可见性修饰符。
     * Getter 始终具有与其属性相同的可见性。
     * Kotlin 中有四种可见性修饰符： private(仅本类可见) 、 protected(子类也可见) 、 internal(同模块可见)和public(都可见) 。默认可见性是public 。
     */

    /**
     * 拓展方法和属性
     * 如果和类本身的方法和属性重复, 将使用类本身的方法或属性
     */
    @Test
    fun testExtensions() {
        /**
         * 拓展方法, 可以定义在函数内部或外部
         */
        fun Point.sum() = this.x + this.y

        val point = Point(1, 2)
        Assertions.assertEquals(point.sum(), 3)
        Assertions.assertEquals(point.index, 1)
    }

    /**
     * Kotlin 中的数据类主要用于保存数据。对于每个数据类，编译器会自动生成附加成员函数
     * .equals()/.hashCode(),.copy(),.toString()
     * .componentN()函数对应于属性的声明顺序。
     *
     * .toString() 、 .equals() 、 .hashCode()和.copy()默认情况下, 只会使用类头的属性
     * data类的要求
     * 1. 主构造函数必须至少有一个参数。
     * 2. 所有主要构造函数参数必须标记为val或var 。
     * 3. 数据类不能是抽象的、开放的、密封的或内部的。
     */
    @Test
    fun testData() {
        data class Line(val start: Int, val end: Int)
        val line = Line(1,2)
        // 通过componentN函数访问
        var (start, end) = line
        Assertions.assertEquals(start, 1)
        Assertions.assertEquals(end, 2)
    }

    /**
     * 密封类和接口提供类层次结构的受控继承。
     * 密封类的所有直接子类在编译时都是已知的。
     * 定义密封类的模块和包之外不得出现其他子类。
     */
    @Test
    fun testSealed() {
        val error = Error.NetworkError()
    }

    /**
     * 泛形
     */
    @Test
    fun testGenerics() {
        val sourceOut: Source<Int> = Source<Int> { -> 1 }
        val source: Source<Number> = sourceOut
        Assertions.assertEquals(source.nextT(), 1)

        val compareTo: Comparable<Number> = Comparable<Number> { v -> v.toInt() }
        val compareTo1: Comparable<Float> = compareTo
        compareTo.compareTo(1.1F)
    }

    /**
     * 标记为inner嵌套类可以访问其外部类的成员。内部类携带对外部类对象的引用：
     */
    @Test
    fun testInnerClass() {
        val out = OutClass().InnerClass().accessOut
        Assertions.assertEquals(out, 1)
    }

    @Test
    fun testEnum() {
        Assertions.assertNotEquals(Direction.EAST, Direction.NORTH)
    }

    /**
     * 生成只使用一次的匿名类
     */
    @Test
    fun testObjectExpressions() {
        val o = object: Source<Int> {
            override fun nextT(): Int = 1
        }
        Assertions.assertEquals(o.nextT(), 1)
    }

    @Test
    fun testSingleton() {
        Assertions.assertEquals(Singleton.label, "Singleton")
    }

    @Test
    fun testObjectDelegation() {
        val a = BaseAImpl()
        Assertions.assertEquals(a.printA(), "BaseAImpl")
        Assertions.assertEquals(a.printB(), "BaseAImpl")
        Assertions.assertEquals(a.printC(), "BaseAImpl")

        val b = BaseBImpl()
        Assertions.assertEquals(b.printA(), "BaseBImpl")
        Assertions.assertEquals(b.printB(), "BaseAImpl")
        Assertions.assertEquals(b.printC(), "BaseAImpl")

        val c = BaseCImpl(b)
        Assertions.assertEquals(c.printA(), "BaseCImpl")
        Assertions.assertEquals(c.printB(), "BaseAImpl")
        Assertions.assertEquals(c.printC(), "BaseAImpl")

    }

    @Test
    fun testPropertiesDelegation() {
        // 使用map委托属性
        val map = mapOf("name" to "shug", "age" to 18)
        val name: String by map
        val age: Int by map
        Assertions.assertEquals(name, "shug")
        Assertions.assertEquals(age, 18)

        val delegate: Delegate = Delegate()
        Assertions.assertEquals(delegate.property, "delegating property")
        Assertions.assertEquals(delegate.lazyValue, "lazyValue")
        Assertions.assertEquals(delegate.observable, "observable")
        Assertions.assertEquals(delegate.vetoable, "vetoable")
        delegate.vetoable = "new vetoable"
        Assertions.assertEquals(delegate.vetoable, "vetoable")

    }
}

// 拓展属性, 不能定义在函数内部
val Point.index: Int
    get() = 1