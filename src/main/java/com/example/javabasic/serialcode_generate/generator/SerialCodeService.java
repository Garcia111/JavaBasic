//package com.example.javabasic.serialcode.generator;
//
//import com.example.javabasic.entity.ObscureObj;
//import com.xuanner.seq.sequence.Sequence;
//import lombok.RequiredArgsConstructor;
//import lombok.extern.slf4j.Slf4j;
//import org.joda.time.DateTime;
//import org.joda.time.Period;
//import org.joda.time.PeriodType;
//import org.joda.time.format.DateTimeFormat;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.stereotype.Service;
//
///**
// * @author：Cheng.
// * @date：Created in 10:51 2019/11/26
// */
//@Slf4j
//@Service
//@RequiredArgsConstructor(onConstructor_ = @Autowired)
//public class SerialCodeService {
//
//    private static final DateTime INIT_DATETIME =
//            DateTime.parse("20100101", DateTimeFormat.forPattern("yyyyMMdd"));
//
//    private final RandomService randomService;
//
//    private final Sequence orderCodeSequence;
//
//    public String orderCode() {
//        // 获得天数差
//        Period p = new Period(INIT_DATETIME, DateTime.now(), PeriodType.days());
//        int bm = p.getDays();
//
//        long seq = orderCodeSequence.nextValue();
//        ObscureObj obj =
//                ObscureObj.builder().length(6).padChar('0').step(3).days(bm).serial(seq).build();
//
//        log.info("开始生成订单代码，参数值：[{}]", obj);
//        String rtn = randomService.obscure(obj);
//        return rtn;
//    }
//}
