package com.common.aspect;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.common.entity.BaseEntity;
import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Component;

import java.util.UUID;

@Aspect
@Slf4j
@Component
@Lazy(value = false)
/*
* 通过切面生成一个uuid当作主键
* 通过 MybatisPlusAutoConfiguration 实现控制全局通用数据新增了 取代切面的方式
* */
public class AutoIncrementId {

    /**
     * 定义切入点：对要拦截的方法进行定义与限制，如包、类
     *
     * 1、execution(public * *(..)) 任意的公共方法
     * 2、execution（* set*（..）） 以set开头的所有的方法
     * 3、execution（* com.lingyejun.annotation.LoggerApply.*（..））com.lingyejun.annotation.LoggerApply这个类里的所有的方法
     * 4、execution（* com.lingyejun.annotation.*.*（..））com.lingyejun.annotation包下的所有的类的所有的方法
     * 5、execution（* com.lingyejun.annotation..*.*（..））com.lingyejun.annotation包及子包下所有的类的所有的方法
     * 6、execution(* com.lingyejun.annotation..*.*(String,?,Long)) com.lingyejun.annotation包及子包下所有的类的有三个参数，第一个参数为String类型，第二个参数为任意类型，第三个参数为Long类型的方法
     * 7、@annotation(com.fescotech.contract.aop.myannotations.SignUpdateStatus)
     * @Pointcut("execution( * com.example.aop..*.*(..))")
     */
    //@Pointcut("execution( *com.example.*save*(..)),execution(* inset*(..)),execution(* add*(..))")
    @Pointcut("@annotation(com.common.annotation.AutoId)")
    private void cutMethod() {
    }
    /**
     * 环绕通知：灵活自由的在目标方法中切入代码
     * 建议返回Obj类型  该方法返回类型会替代原方法返回类型 所以最后统一都用Obj接收  避免类型转换异常
     */
    @Around("cutMethod()")
    public Object around(ProceedingJoinPoint joinPoint) throws Throwable {
        // 获取目标方法的名称
        String methodName = joinPoint.getSignature().getName();
        // 获取方法传入参数
        Object[] params = joinPoint.getArgs();
        //一个参数
        BaseEntity baseEntity = (BaseEntity)params[0];
        UUID uuid = UUID.randomUUID();
        baseEntity.setId(uuid.toString());
        baseEntity.setCreator("gz");
        //SignUpdateStatus CommentInte = getDeclaredAnnotation(joinPoint);
        log.info("环绕通知 切入到方法前 " + methodName + " args " + params[0]);
        // 执行源方法
        Object proceed = joinPoint.proceed();
        log.info("环绕通知 切入到方法后"+proceed);
        return proceed;
    }
}