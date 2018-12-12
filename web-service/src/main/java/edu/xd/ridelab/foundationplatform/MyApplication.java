package edu.xd.ridelab.foundationplatform; /**
 * Created by Administrator on 2018/5/3.
 *
 * @
 */

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 *@Author xuziheng
 *@Date 2018/5/3 16:22
 */

@SpringBootApplication
@MapperScan("edu.xd.ridelab.foundationplatform.mapperInterface")
public class MyApplication {
	public static void main(String[] args) {
		SpringApplication.run(MyApplication.class, args);
	}
}
