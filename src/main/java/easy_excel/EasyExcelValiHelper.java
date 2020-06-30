package easy_excel;

import com.alibaba.excel.annotation.ExcelProperty;

import javax.validation.ConstraintViolation;
import javax.validation.Validation;
import javax.validation.Validator;
import javax.validation.groups.Default;
import java.lang.reflect.Field;
import java.util.Set;


public class EasyExcelValiHelper {

    private EasyExcelValiHelper(){}

    private static Validator validator = Validation.buildDefaultValidatorFactory().getValidator();

    /**
     * 校验导入数据的格式是否正确
     * @param obj
     * @param <T>
     * @return
     * @throws NoSuchFieldException
     */
    public static <T> String validateEntity(T obj) throws NoSuchFieldException {
        StringBuilder result = new StringBuilder();
        //获取格式校验不通过的属性集合
        Set<ConstraintViolation<T>> set = validator.validate(obj, Default.class);
        if (set != null && !set.isEmpty()) {
            for (ConstraintViolation<T> cv : set) {
                //cv.getPropertyPath()即为获取属性名
                Field declaredField = obj.getClass().getDeclaredField(cv.getPropertyPath().toString());
                ExcelProperty annotation = declaredField.getAnnotation(ExcelProperty.class);
                //拼接错误信息，包含当前出错数据的标题名字+错误信息
                result.append(annotation.value()[0]+cv.getMessage()).append(";");
            }
        }
        return result.toString();
    }
}