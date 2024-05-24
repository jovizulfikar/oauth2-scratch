package com.example.oauth2infra.jpa.repository;

import com.example.oauth2infra.jpa.entity.JpaClient;
import com.example.oauth2infra.util.MapperUtil;
import com.oauth2core.domain.entity.Client;
import com.oauth2core.port.repository.ClientRepository;
import com.oauth2core.port.util.IdGenerator;
import jakarta.persistence.EntityManager;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import java.util.Objects;
import java.util.Optional;

@Repository
@RequiredArgsConstructor
public class JpaQueryBuilderClientRepository implements ClientRepository {

    private final EntityManager entityManager;
    private final MapperUtil mapper;
    private final IdGenerator idGenerator;

    @Override
    public Optional<Client> findByClientId(String clientId) {
        var criteriaBuilder = entityManager.getCriteriaBuilder();
        var criteriaQuery = criteriaBuilder.createQuery(JpaClient.class);
        var root = criteriaQuery.from(JpaClient.class);

        criteriaQuery.select(root)
                .where(criteriaBuilder.equal(root.get("clientId"), clientId));

        return entityManager.createQuery(criteriaQuery)
                .getResultList()
                .stream()
                .findFirst()
                .map(mapper::jpaClientToClient);
    }

    @Override
    @Transactional
    public Client save(Client client) {
        if (Objects.isNull(client.getId())) {
            cleanEntity(client);
            entityManager.persist(mapper.clientToJpaClient(client));
            return client;
        }

        var existingClient = entityManager.find(JpaClient.class, client.getClientId());
        cleanEntity(client);
        if (Objects.isNull(existingClient)) {
            entityManager.persist(mapper.clientToJpaClient(client));
        } else {
            entityManager.merge(mapper.clientToJpaClient(client));
        }

        return client;
    }

    private void cleanEntity(Client client) {
        if (Objects.isNull(client.getId())) {
            client.setId(idGenerator.generate());
        }

        client.getSecrets().forEach(secret -> {
            secret.setClientId(client.getId());
            if (Objects.isNull(secret.getId())) {
                secret.setId(idGenerator.generate());
            }
        });
    }
}