package vn.edu.iuh.fit.backend.services;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.*;
import org.springframework.stereotype.Service;
import vn.edu.iuh.fit.backend.models.Candidate;
import vn.edu.iuh.fit.backend.models.JobSkill;
import vn.edu.iuh.fit.backend.models.Skill;
import vn.edu.iuh.fit.backend.repositories.CandidateRepository;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
@Service
public class CandidateServices {
    @Autowired
    private CandidateRepository candidateRepository;

    // Lưu thông tin ứng viên mới
    public void saveCandidate(Candidate candidate) {
        candidateRepository.save(candidate);
    }
    public Page<Candidate> findAll(int pageNo, int pageSize, String sortBy, String sortDirection) {
        Sort sort = Sort.by(Sort.Direction.fromString(sortDirection), sortBy);
        Pageable pageable = PageRequest.of(pageNo, pageSize, sort);
//        return candidateRepository.findAll(pageable);//findFirst.../findTop...
        return candidateRepository.findAllByStatus(1, pageable);//findFirst.../findTop...
    }

    public Page<Candidate> findPaginated(Pageable pageable) {
        int pageSize = pageable.getPageSize();
        int currentPage = pageable.getPageNumber();
        int startItem = currentPage * pageSize;
        List<Candidate> list;
        List<Candidate> candidates = candidateRepository.findAll();

        if (candidates.size() < startItem) {
            list = Collections.emptyList();
        } else {
            int toIndex = Math.min(startItem + pageSize, candidates.size());
            list = candidates.subList(startItem, toIndex);
        }

        Page<Candidate> candidatePage
                = new PageImpl<>(list, PageRequest.of(currentPage, pageSize), candidates.size());

        return candidatePage;
    }
    public Candidate authenticate(String username, String password) {
        return candidateRepository.findByCandidateLoginAndCandidatePassword(username, password);
    }
    // Lấy tất cả ứng viên
    public List<Candidate> getAllCandidates() {
        return candidateRepository.findAll();
    }

    public List<Candidate> getCandidatesBySkillId(Long skillId) {
        // Tìm ứng viên có kỹ năng tương ứng với skillId
        return candidateRepository.findCandidatesBySkillId(skillId);
    }
    public List<Candidate> getCandidatesBySkill(Long skillId) {
        return candidateRepository.findBySkillId(skillId);
    }
    // Lấy các ứng viên theo kỹ năng
    public List<Candidate> getCandidatesBySkills(List<Skill> skills) {
        return candidateRepository.findCandidatesBySkills(skills);
    }
    // Truy vấn ứng viên theo kỹ năng
    public List<Candidate> findCandidatesBySkill(String skillName) {
        return candidateRepository.findCandidatesBySkill(skillName);
    }

    // Lấy thông tin ứng viên và kỹ năng của họ
    public List<Candidate> getCandidateWithSkills(long candidateId) {
        return candidateRepository.findCandidateWithSkills(candidateId);
    }
    public Candidate findById(Long id) {
        return candidateRepository.findById(id).orElse(null);
    }


}
