package site.shug.spring.common.dao;

import org.springframework.data.annotation.Id;
import org.springframework.data.relational.core.mapping.Table;

/**
 * Java Record 是 Java 14 中引入的一种新的语法特性，用于简化不可变数据类的定义。Record 是一种特殊的类，旨在减少样板代码（boilerplate code）的编写，特别是对于只包含数据且不需要复杂逻辑的类。
 *
 * ### 主要特点：
 *
 * 1. **简化类定义**：使用 Record 定义一个类时，编译器会自动生成常见的成员方法，如构造器、`equals()`、`hashCode()`、`toString()`等。
 *
 * 2. **不可变性**：Record 的字段默认是 `final` 的，这意味着一旦对象被创建，这些字段的值就不能再更改。
 *
 * 3. **简洁语法**：Record 的定义比普通类更简洁，只需要指定字段，其他的代码由编译器生成。
 *
 * ### 语法示例：
 * ```java
 * public record Person(String name, int age) {}
 * ```
 *
 * 在上面的代码中，`Person` 是一个 Record，它有两个字段：`name` 和 `age`。编译器会自动生成以下内容：
 * - 私有的、不可变的字段 `name` 和 `age`。
 * - 一个构造函数 `Person(String name, int age)`。
 * - `equals()` 方法，用于比较两个 `Person` 对象。
 * - `hashCode()` 方法，用于生成哈希值。
 * - `toString()` 方法，返回 `Person[name=..., age=...]` 形式的字符串。
 *
 * ### 使用场景：
 *
 * Record 特别适合用于创建只保存数据的简单类，例如传输对象（DTOs）、返回值包装器等。在这些场景下，它能显著减少冗长代码，使代码更清晰、更易于维护。
 *
 * ### 限制：
 * - Record 是 `final` 的，不能被继承。
 * - Record 不能有非 `final` 字段。
 * - Record 只能实现接口，不能继承其他类。
 *
 */
@Table
public record RecordUser(@Id Long id, String name, String nickname, int age) {
}
