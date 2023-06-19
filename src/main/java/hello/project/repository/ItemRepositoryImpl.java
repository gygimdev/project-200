package hello.project.repository;

import jakarta.persistence.EntityManager;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@RequiredArgsConstructor
public class ItemRepositoryImpl implements ItemRepositoryCustom{

    private final EntityManager em;

}
