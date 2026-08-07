package com.example.cloudassignment.member.service;

import com.example.cloudassignment.global.exception.MemberNotFoundException;
import com.example.cloudassignment.global.exception.ProfileImageNotFoundException;
import com.example.cloudassignment.member.dto.MemberCreateRequest;
import com.example.cloudassignment.member.dto.MemberResponse;
import com.example.cloudassignment.member.entity.Member;
import com.example.cloudassignment.member.repository.MemberRepository;
import com.example.cloudassignment.storage.S3StorageService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

@Service
@RequiredArgsConstructor
public class MemberService {

    private final MemberRepository memberRepository;
    private final S3StorageService s3StorageService;

    @Transactional
    public MemberResponse create(MemberCreateRequest request) {

        Member member = new Member(
                request.getName(),
                request.getAge(),
                request.getMbti()
        );
        Member createdMember = memberRepository.save(member);
        return MemberResponse.from(createdMember);
    }


    @Transactional(readOnly = true)
    public MemberResponse getMember(Long memberId) {
        Member member = memberRepository.findById(memberId).orElseThrow(
                () -> new MemberNotFoundException(memberId)
        );
        return MemberResponse.from(member);

    }

    @Transactional
    public void uploadProfileImage(Long memberId, MultipartFile file) {
        Member member = memberRepository.findById(memberId).orElseThrow(
                () -> new MemberNotFoundException(memberId)
        );
        String objectKey = s3StorageService.uploadProfileImage(memberId, file);
        member.updateProfileImageKey(objectKey);
    }

    @Transactional(readOnly = true)
    public String getProfileImageUrl(Long memberId) {
        Member member = memberRepository.findById(memberId).orElseThrow(
                () -> new MemberNotFoundException(memberId)
        );

        String objectKey = member.getProfileImageKey();

        if (objectKey == null || objectKey.isBlank()) {
            throw new ProfileImageNotFoundException(memberId);
        }

        return s3StorageService.createPresignedUrl(objectKey);
    }

}
