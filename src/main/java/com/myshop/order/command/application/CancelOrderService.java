package com.myshop.order.command.application;

import com.myshop.order.NoOrderException;
import com.myshop.order.command.domain.*;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/*
* 응용 영역 :
*   기능을 구현하는 영역,
*   기능을 구현하기 위해 도메인 영역의 도메인 모델을 사용
 */
@Service
public class CancelOrderService {
    private OrderRepository orderRepository;
    private CancelPolicy cancelPolicy;

    public CancelOrderService(OrderRepository orderRepository,
                              CancelPolicy cancelPolicy) {
        this.orderRepository = orderRepository;
        this.cancelPolicy = cancelPolicy;
    }

    @Transactional
    public void cancel(OrderNo orderNo, Canceller canceller) {
        Order order = orderRepository.findById(orderNo)
                .orElseThrow(() -> new NoOrderException());
        if (!cancelPolicy.hasCancellationPermission(order, canceller)) {
            throw new NoCancellablePermission();
        }
        order.cancel(); // 도메인 모델에 도메인 로직 수행을 위임하여 기능 구현
    }

}
