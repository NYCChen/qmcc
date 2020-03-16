package com.qmcc.sys.cache;

import com.qmcc.sys.domain.Dept;
import com.qmcc.sys.vo.DeptVo;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.beans.BeanUtils;
import org.springframework.context.annotation.EnableAspectJAutoProxy;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.Map;

@Aspect
@Component
@EnableAspectJAutoProxy
public class CacheAspect {

    // 声明一个缓存容器
    private Map<String, Object> CACHE_CONTAINER = new HashMap<>();

    //声明切面表达式
    private static final String POINTCUT_DEPT_UPDATE =
            "execution(* com.qmcc.sys.service.impl.DeptServiceImpl.updateById(..))";
    private static final String POINTCUT_DEPT_GET =
            "execution(* com.qmcc.sys.service.impl.DeptServiceImpl.getOne(..))";
    private static final String POINTCUT_DEPT_DELETE =
            "execution(* com.qmcc.sys.service.impl.DeptServiceImpl.removeById(..))";

    private static final String CACHE_DEPT_PROFIX="dept";

    /**
     * 查询切入
     */
    @Around(value=POINTCUT_DEPT_GET)
    public Object cacheDeptGet(ProceedingJoinPoint joinPoint) throws Throwable {
        // 取出第一个参数
        Integer object = (Integer) joinPoint.getArgs()[0];
        //从缓存里面取
        Object res1 = CACHE_CONTAINER.get(CACHE_DEPT_PROFIX+object);
        if (res1 != null){
            //在缓存里面取到了，就不用去查询数据库
            return res1;
        } else {
            //在缓存里面没取到，就放行，去数据库查
            Dept res2 = (Dept) joinPoint.proceed();
            CACHE_CONTAINER.put(CACHE_DEPT_PROFIX + res2.getId(), res2);
            return res2;
        }
    }

    /**
     * 更新切入
     */
    @Around(value=POINTCUT_DEPT_UPDATE)
    public Object cacheDeptUpdate(ProceedingJoinPoint joinPoint) throws Throwable {
        // 取出第一个参数
        DeptVo deptVo = (DeptVo) joinPoint.getArgs()[0];
        Boolean isSuccess = (Boolean) joinPoint.proceed();
        if (isSuccess){
            Dept dept = (Dept) CACHE_CONTAINER.get(CACHE_DEPT_PROFIX+deptVo.getId());
            if (null == dept){
                dept = new Dept();
                BeanUtils.copyProperties(deptVo, dept);
                CACHE_CONTAINER.put(CACHE_DEPT_PROFIX+dept.getId(), dept);
            }
        }
        return isSuccess;
    }

    /**
     * 删除切入
     */
    @Around(value=POINTCUT_DEPT_DELETE)
    public Object cacheDeptDelete(ProceedingJoinPoint joinPoint) throws Throwable {
        // 取出第一个参数
        Integer id = (Integer) joinPoint.getArgs()[0];
        Boolean isSuccess = (Boolean) joinPoint.proceed();
        if(isSuccess) {
            //删除缓存
            CACHE_CONTAINER.remove(CACHE_DEPT_PROFIX+id);
        }
        return isSuccess;
    }


}
