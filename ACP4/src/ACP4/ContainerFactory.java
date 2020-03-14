package ACP4;

import java.util.List;
import java.util.Map;

public interface ContainerFactory {
    Map<Object, Object> createObjectContainer();

    List<?> creatArrayContainer();
}
