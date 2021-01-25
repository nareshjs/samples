package nj.samples;

import java.util.Arrays;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.expression.Expression;
import org.springframework.expression.ExpressionParser;
import org.springframework.expression.spel.standard.SpelExpressionParser;
import org.springframework.expression.spel.support.StandardEvaluationContext;

@Slf4j
@SpringBootApplication
public class SpelExampleParser implements CommandLineRunner {
  public static void main(String[] args) {
    SpringApplication.run(SpelExampleParser.class, args);
  }

  @Override
  public void run(String... args) throws Exception {
    ExpressionParser parser = new SpelExpressionParser();

    Expression expression = parser.parseExpression("'Hi'");
    System.out.println(expression.getValue(String.class));

    List<String> subjects = Arrays.asList(new String[]{"Maths", "Commerce", "Economics"}.clone());
    Map<String, Integer> marks = new LinkedHashMap<>();
    marks.put("Maths", 100);
    marks.put("Commerce", 99);
    marks.put("Economics", 98);
    StudentBean student = StudentBean.builder().id(99L).firstName("Stu1")
        .lastName("D")
        .subjects(subjects)
        .marks(marks).build();

    //Expensive op, so cache the context for the bean if required for evaluating another expression
    StandardEvaluationContext context = new StandardEvaluationContext(student);
    expression = parser.parseExpression("firstName != 'Stu2'");
    log.info("" + expression.getValue(context, Boolean.class));

    expression = parser.parseExpression("id");
    log.info("" + expression.getValue(context, Long.class));

    expression = parser.parseExpression("subjects.size() == 4");
    log.info("" + expression.getValue(context, Boolean.class));

    expression = parser.parseExpression("subjects[0] == 'Maths'");
    log.info("" + expression.getValue(context, Boolean.class));

    expression = parser.parseExpression("marks['Economics'] == 98 && marks['Commerce'] gt 75");
    log.info("" + expression.getValue(context, Boolean.class));

    expression = parser.parseExpression("marks['Economics'] == 90");
    log.info("" + expression.getValue(context, Boolean.class));

    log.info(student.getFullName());
    expression = parser.parseExpression("firstName.concat(' ').concat(lastName)");
    String fullNameEval = expression.getValue(context, String.class);
    parser.parseExpression("fullName").setValue(context, fullNameEval);
    log.info(student.getFullName());
  }
}
