package co.id.bca.cros.shared.helper.db;

import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.persistence.Query;
import lombok.RequiredArgsConstructor;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.BeanPropertySqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Component;

import java.lang.reflect.Field;
import java.util.List;

@Component
@RequiredArgsConstructor
public class DbHelper {

    @PersistenceContext
    private EntityManager entityManager;
    private final NamedParameterJdbcTemplate namedParameterJdbcTemplate;

    public <T> List<T> executeQueryEm(String sql, Object params, Class<T> resultClass) {
        Query query = entityManager.createNativeQuery(sql, resultClass);
        bindDtoParameters(query, params);
        return query.getResultList();
    }

    public <T> List<T> executeQueryJdbc(String sql, Object params, RowMapper<T> mapper) {
        BeanPropertySqlParameterSource parameterSource = new BeanPropertySqlParameterSource(params);
        return namedParameterJdbcTemplate.query(sql, parameterSource, mapper);
    }

    private void bindDtoParameters(Query query, Object params) {
        if (params != null) {
            Field[] fields = params.getClass().getDeclaredFields();
            for (Field field : fields) {
                field.setAccessible(true); // Allow access to private fields
                try {
                    Object value = field.get(params);
                    query.unwrap(org.hibernate.query.Query.class).setParameter(field.getName(), value, field.getType());
                } catch (IllegalAccessException e) {
                    throw new RuntimeException("Failed to bind parameter: " + field.getName(), e);
                }
            }
        }
    }
}
