# User Service Read me

```java.lang.IllegalArgumentException: The class with com.ahmad.user_service.security.models.CustomUserDetails and name of com.ahmad.user_service.security.models.CustomUserDetails is not in the allowlist. If you believe this class is safe to deserialize, please provide an explicit mapping using Jackson annotations or by providing a Mixin. If the serialization is only done by a trusted source, you can also enable default typing. See https://github.com/spring-projects/spring-security/issues/4370 for details```

- explicitly annotate the class with `@JsonDeserialize`
- Add `@NoArgsConstructor` allowing Jackson to deserialize it


## Jackson's third-party library object not in the allowlist 
```java.lang.IllegalArgumentException: The class with org.hibernate.collection.spi.PersistentBag and name of org.hibernate.collection.spi.PersistentBag is not in the allowlist. If you believe this class is safe to deserialize, please provide an explicit mapping using Jackson annotations or by providing a Mixin. If the serialization is only done by a trusted source, you can also enable default typing. See https://github.com/spring-projects/spring-security/issues/4370 for details (through reference chain: com.ahmad.user_service.security.models.CustomUserDetails["user"]->com.ahmad.user_service.models.User["roles"])```
- Unrecommended solution is to make roles to be fetched eagerly.
- Avoiding PersistentBag usage in third-party library by structuring `roles` with Set and converting PersistentBag to Set type in `getter`
- You can also apply default typing or define a mixin.

## Adding custom details to JWT



