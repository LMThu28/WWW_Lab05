# www_lab_week5
## Sinh viên thực hiện:

Họ tên: [Lê Minh Thư]

MSSV: [21025781]

## Đề bài thực hành tuần 5 môn Lập trình WWW:
![image](https://github.com/user-attachments/assets/6e77c520-49e4-4bbc-a59e-5663d138cc4b)
![image](https://github.com/user-attachments/assets/ce9d95be-f735-464d-b273-05aa2ef29d50)


## Mô tả

Dự án Jakarta EE này được phát triển để quản lý  trang web cho phép công ty đăng tin tuyển người với các skill mong muốn. Các ứng viên khi log vào sẽ được gợi ý các công việc có skill phù hợp với mình và đề xuất các skill nhân viên có thể học. Giúp các công ty tìm các ứng viên có skill phù hợp và các yêu cầu khác.

## Yêu cầu

Đảm bảo bạn đã cài đặt đầy đủ các thành phần sau:
- Java Development Kit (JDK)
- Maven
- MariaDB

## Công nghệ sử dụng

- Spring Boot: Framework để xây dựng ứng dụng doanh nghiệp dựa trên Java.
- MariaDB: Hệ thống quản lý cơ sở dữ liệu quan hệ.

## Cách cài đặt

1. **Clone dự án từ GitHub hoặc GitLab:**

```
git clone [https://github.com/LMThu2811/www_lab_week5.git](https://github.com/LMThu2811/www_lab_week5.git)
```

2. **Mở dự án bằng một trình biên soạn mã Java:** IntelliJ IDEA.

3. **Cấu hình trong file application.properties:**
 ```
  spring.datasource.driver-class-name=org.mariadb.jdbc.Driver
  spring.datasource.url=jdbc:mariadb://localhost:3306/lab5
  spring.datasource.username=root
  spring.datasource.password=sapass
  spring.jpa.hibernate.ddl-auto=update
  spring.jpa.show-sql=true
  server.port=8080
  
  spring.jpa.hibernate.naming.implicit-strategy=org.hibernate.boot.model.naming.ImplicitNamingStrategyLegacyJpaImpl
  spring.jpa.hibernate.naming.physical-strategy=org.hibernate.boot.model.naming.PhysicalNamingStrategyStandardImpl
  ```
4. **Kết nối database Mariadb:**
   ### Database script:
   ```
     -- Dumping database structure for works
      CREATE DATABASE IF NOT EXISTS `works` /*!40100 DEFAULT CHARACTER SET latin1
      COLLATE latin1_swedish_ci */;
      USE `works`;
      -- Dumping structure for table works.address
      CREATE TABLE IF NOT EXISTS `address` (
      `id` bigint(20) NOT NULL AUTO_INCREMENT,
      `street` varchar(150) DEFAULT NULL,
      `city` varchar(50) DEFAULT NULL,
      `country` smallint(6) DEFAULT NULL CHECK (`country` between 0 and 201),
      `number` varchar(20) DEFAULT NULL,
      `zipcode` varchar(7) DEFAULT NULL,
      PRIMARY KEY (`id`)
      ) ENGINE=InnoDB DEFAULT CHARSET=latin1 COLLATE=latin1_swedish_ci;
      -- Data exporting was unselected.
      -- Dumping structure for table works.candidate
      CREATE TABLE IF NOT EXISTS `candidate` (
      `id` bigint(20) NOT NULL AUTO_INCREMENT,
      `dob` date NOT NULL,
      `email` varchar(255) NOT NULL,
      `full_name` varchar(255) NOT NULL,
      `phone` varchar(15) NOT NULL,
      `address` bigint(20) NOT NULL,
      PRIMARY KEY (`id`),
      UNIQUE KEY `UK_qfut8ruekode092nlkipgl09g` (`email`),
      UNIQUE KEY `UK_9i5yt1gvm0chg5e10qkns7tll` (`phone`),
      UNIQUE KEY `UK_m8qhlm4wu215gr34dhxp0dour` (`address`),
      CONSTRAINT `FKa8gnyyhbb2qnhp94grci1n0o9` FOREIGN KEY (`address`)
      REFERENCES `address` (`id`)
      ) ENGINE=InnoDB DEFAULT CHARSET=latin1 COLLATE=latin1_swedish_ci;
      -- Data exporting was unselected.
      -- Dumping structure for table works.candidate_skill
      CREATE TABLE IF NOT EXISTS `candidate_skill` (
      `more_infos` varchar(1000) DEFAULT NULL,
      `skill_level` tinyint(4) NOT NULL CHECK (`skill_level` between 0 and 2),
      `skill_id` bigint(20) NOT NULL,
      `can_id` bigint(20) NOT NULL,
      PRIMARY KEY (`can_id`,`skill_id`),
      KEY `FKb7cxhiqhcah7c20a2cdlvr1f8` (`skill_id`),
      CONSTRAINT `FKb0m5tm3fi0upa3b3kjx3vrlxs` FOREIGN KEY (`can_id`) REFERENCES
      `candidate` (`id`),
      CONSTRAINT `FKb7cxhiqhcah7c20a2cdlvr1f8` FOREIGN KEY (`skill_id`)
      REFERENCES `skill` (`skill_id`)
      ) ENGINE=InnoDB DEFAULT CHARSET=latin1 COLLATE=latin1_swedish_ci;-- Data exporting was unselected.
      -- Dumping structure for table works.company
      CREATE TABLE IF NOT EXISTS `company` (
      `comp_id` bigint(20) NOT NULL AUTO_INCREMENT,
      `about` varchar(2000) DEFAULT NULL,
      `email` varchar(255) NOT NULL,
      `comp_name` varchar(255) NOT NULL,
      `phone` varchar(255) NOT NULL,
      `web_url` varchar(255) DEFAULT NULL,
      `address` bigint(20) NOT NULL,
      PRIMARY KEY (`comp_id`),
      UNIQUE KEY `UK_rvp2hunsq4sgmpxe3a7i1ym3m` (`address`),
      CONSTRAINT `FKd5occp4cjwihejbxdbpvkp5tv` FOREIGN KEY (`address`)
      REFERENCES `address` (`id`)
      ) ENGINE=InnoDB DEFAULT CHARSET=latin1 COLLATE=latin1_swedish_ci;
      -- Data exporting was unselected.
      -- Dumping structure for table works.job
      CREATE TABLE IF NOT EXISTS `job` (
      `job_id` bigint(20) NOT NULL AUTO_INCREMENT,
      `job_desc` varchar(2000) NOT NULL,
      `job_name` varchar(255) NOT NULL,
      `company` bigint(20) DEFAULT NULL,
      PRIMARY KEY (`job_id`),
      KEY `FKbaqlvluu78phmo9ld89um7wnm` (`company`),
      CONSTRAINT `FKbaqlvluu78phmo9ld89um7wnm` FOREIGN KEY (`company`)
      REFERENCES `company` (`comp_id`)
      ) ENGINE=InnoDB DEFAULT CHARSET=latin1 COLLATE=latin1_swedish_ci;
      -- Data exporting was unselected.
      -- Dumping structure for table works.job_skill
      CREATE TABLE IF NOT EXISTS `job_skill` (
      `more_infos` varchar(1000) DEFAULT NULL,
      `skill_level` tinyint(4) NOT NULL CHECK (`skill_level` between 0 and 2),
      `job_id` bigint(20) NOT NULL,
      `skill_id` bigint(20) NOT NULL,
      PRIMARY KEY (`job_id`,`skill_id`),
      KEY `FKj33qbbf3vk1lvhqpcosnh54u1` (`skill_id`),
      CONSTRAINT `FK9ix4wg520ii2gu2felxdhdup6` FOREIGN KEY (`job_id`) REFERENCES
      `job` (`job_id`),
      CONSTRAINT `FKj33qbbf3vk1lvhqpcosnh54u1` FOREIGN KEY (`skill_id`)
      REFERENCES `skill` (`skill_id`)
      ) ENGINE=InnoDB DEFAULT CHARSET=latin1 COLLATE=latin1_swedish_ci;
      -- Data exporting was unselected.
      -- Dumping structure for table works.skill
      CREATE TABLE IF NOT EXISTS `skill` (
      `skill_id` bigint(20) NOT NULL AUTO_INCREMENT,
      `skill_description` varchar(255) DEFAULT NULL,
      `skill_name` varchar(255) DEFAULT NULL,
      `type` tinyint(4) DEFAULT NULL CHECK (`type` between 0 and 2),
      PRIMARY KEY (`skill_id`)) ENGINE=InnoDB DEFAULT CHARSET=latin1 COLLATE=latin1_swedish_ci;
   ```
![image](https://github.com/user-attachments/assets/06c98373-e8d4-4bcb-9ce3-9b849a9e205c)




6. **Cấu trúc project:**
  - **models:** Package chứa các lớp mô hình (entities) của cơ sở dữ liệu.

    + Address.java: Đại diện cho thông tin địa chỉ.
    + Candidate.java: Đại diện cho thông tin ứng viên.
    + CandidateSkill.java: Đại diện cho kỹ năng của ứng viên.
    + Company.java: Đại diện cho thông tin công ty.
    + Job.java: Đại diện cho thông tin công việc.
    + JobSkill.java: Đại diện cho kỹ năng yêu cầu của công việc.
    + Skill.java: Đại diện cho thông tin kỹ năng.

 - **dao:** Bao gồm các DAO (Data Access Object) chứa các phương thức để truy cập và thao tác dữ liệu trong cơ sở dữ liệu.
    + CandidateDao: chứa các hàm logIn, getCandByYearBorn, getTotalSkillByCand, getTotalJobSkillByCand.
    + CandidateSkillDao: chứa các hàm getJobForCandidateOrderBySkill, getSkillNotHaveToLearn, getCandidateOrderBySkill.
    + CompanyDao: chứa hàm logIn.
    + ExperienceDao: chứa các hàm getMaxExpYearCandidate, getNotHaveExpCandidate.
    + JobDao: chứa các hàm getAllJobList, getAll.
    + JobSkillDao: chứa các hàm addJobSkill, calcProposedSalary.
7. **Chạy ứng dụng:**

![image](https://github.com/user-attachments/assets/b737a92a-f1c1-44d0-962f-c1f8573213ac)
![image](https://github.com/user-attachments/assets/2b1dc615-2881-438b-8fe6-4b2cf891f4e7)
![image](https://github.com/user-attachments/assets/243de3b9-f2c7-4efb-bbd8-9fe7d13b223a)
![image](https://github.com/user-attachments/assets/df6437b1-79f4-40a4-9397-edebd5787a1d)
![image](https://github.com/user-attachments/assets/292b58f9-bc46-4622-82ec-b827b7de41e2)
![image](https://github.com/user-attachments/assets/28fcaf77-62b6-434a-b4a7-47a85b53653d)
![image](https://github.com/user-attachments/assets/1764722f-3eff-4e85-b00e-744a345b6c86)
![image](https://github.com/user-attachments/assets/94405719-2ada-445e-903c-07e822499cc0)












## Tài liệu liên quan

- [Java EE Documentation](https://javaee.github.io/javaee-spec/)
- [Java Database Connectivity (JDBC) Documentation](https://docs.oracle.com/en/java/javase/16/docs/api/java.sql/java/sql/package-summary.html)
- [Apache Tomcat Documentation](https://tomcat.apache.org/tomcat-9.0-doc/index.html)
- [Git Version Control](https://git-scm.com/book/en/v2)




- GitHub Repository: [WWW_Lab05](https://github.com/LMThu28/WWW_Lab05)
---
