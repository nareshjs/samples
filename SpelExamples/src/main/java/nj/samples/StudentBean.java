package nj.samples;

import java.util.List;
import java.util.Map;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Component("student")
public class StudentBean {

  private String firstName;
  private String lastName;

  //@Value("#{student.firstName.concat(' ').concat(student.lastName)}")
  private String fullName;
  private Long id;
  private List<String> subjects;
  private Map<String, Integer> marks;
}
