package com.lk.profile.service;

import com.lk.profile.api.dto.ProfilePatchRequest;
import com.lk.profile.api.dto.ProfileResponse;
import com.lk.user.domain.User;

import java.util.Optional;

/**
 * 个人资料业务接口。
 */
public interface ProfileService {

    Optional<User> getById(long userId);

    ProfileResponse updateProfile(long userId, ProfilePatchRequest req);

    ProfileResponse updateAvatar(long userId, String avatarUrl);
}