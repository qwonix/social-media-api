package ru.qwonix.test.social.media.api.serivce.impl;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ru.qwonix.test.social.media.api.entity.Relation;
import ru.qwonix.test.social.media.api.entity.RelationId;
import ru.qwonix.test.social.media.api.entity.RelationType;
import ru.qwonix.test.social.media.api.entity.UserProfile;
import ru.qwonix.test.social.media.api.repository.RelationRepository;
import ru.qwonix.test.social.media.api.serivce.RelationService;

import java.util.List;


@RequiredArgsConstructor
@Service
public class RelationServiceImpl implements RelationService {

    private final RelationRepository relationRepository;

    @Override
    public List<UserProfile> findAllSubscriptions(UserProfile userProfile) {
        return relationRepository.findAllBySourceUserAndRelationType(userProfile, RelationType.SUBSCRIBER).stream()
                .map(Relation::getTargetUser).toList();
    }

    @Override
    public boolean isSubscriber(UserProfile source, UserProfile target) {
        return relationRepository.existsById(new RelationId(source, target, RelationType.SUBSCRIBER));
    }

    @Override
    public boolean areNotFriends(UserProfile user1, UserProfile user2) {
        return !areFriends(user1, user2);
    }

    @Override
    public boolean areFriends(UserProfile user1, UserProfile user2) {
        return relationRepository.existsById(new RelationId(user1, user2, RelationType.SUBSCRIBER)) &&
               relationRepository.existsById(new RelationId(user2, user1, RelationType.SUBSCRIBER));
    }

    @Override
    public void subscribe(UserProfile source, UserProfile target) {
        relationRepository.save(new Relation(source, target, RelationType.SUBSCRIBER));
    }

    @Override
    public void unsubscribe(UserProfile source, UserProfile target) {
        relationRepository.delete(new Relation(source, target, RelationType.SUBSCRIBER));
    }
}
