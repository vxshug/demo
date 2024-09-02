namespace Demo.tests;

/**
 * C#中的生命周期
 */

ref struct Pointer
{
    ref int PointerInt;
    int Value;

    public Pointer(ref int pointer)
    {
        PointerInt = ref pointer; // PointerInt和pointer在调用方法上都是可用的
    }
    
    public void SetPointer(ref int pointer)
    {
        // PointerInt = ref pointer; // 报错不能保存到PointerInt
    }
    public ref int PassPointer(ref int pointer)
    {
        return ref pointer; // 只可以返回
    }
    public ref int P1 => ref PointerInt; // PointerInt在Pointer的作用域中都可用
    // public ref int P2 => ref Value; // Value不能在Pointer之外使用
    public void IncrementValue()
    {
        ref int value = ref Value; // Value的引用可以在Pointer内部使用
        value++;
    }

    // 报错scoped用来把逃逸范围限制到当前方法
    // public Pointer(scoped ref int pointer)
    // {
    //     PointerInt = ref pointer;
    // }
    
    // 在 C# 11 中，out 参数将会默认为 scoped ref，而 in 参数仍然保持默认为 ref
}

public class LifeTimeTest
{

}