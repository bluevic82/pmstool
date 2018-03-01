-- phpMyAdmin SQL Dump
-- version 4.7.0
-- https://www.phpmyadmin.net/
--
-- Host: 127.0.0.1
-- Generation Time: Mar 01, 2018 at 03:36 AM
-- Server version: 10.1.25-MariaDB
-- PHP Version: 7.1.7

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
SET AUTOCOMMIT = 0;
START TRANSACTION;
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Database: `pms_project_test`
--

-- --------------------------------------------------------

--
-- Table structure for table `anser_question`
--

CREATE TABLE `anser_question` (
  `PROJECT_ID` int(4) NOT NULL,
  `Q_A_ID` int(4) NOT NULL,
  `Q_A_TITLE` varchar(200) CHARACTER SET utf8 NOT NULL,
  `REFERENCEPOINT` varchar(250) CHARACTER SET utf8 NOT NULL,
  `Q_A_QUESTION_VI` varchar(2000) CHARACTER SET utf8 NOT NULL,
  `Q_A_QUESTION_JP` varchar(2000) CHARACTER SET utf8 DEFAULT NULL,
  `Q_A_ANSWER_VI` varchar(2000) CHARACTER SET utf8 DEFAULT NULL,
  `Q_A_ANSWER_JP` varchar(2000) CHARACTER SET utf8 DEFAULT NULL,
  `MEMBER_PROJECT_ID` int(4) NOT NULL,
  `STATUS_ID` tinyint(2) NOT NULL,
  `MEMBER_FROM` int(4) NOT NULL,
  `Q_A_DEALINE` date NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_bin;

-- --------------------------------------------------------

--
-- Table structure for table `bug_info`
--

CREATE TABLE `bug_info` (
  `BUG_ID` int(6) NOT NULL,
  `BUG_SUBJECT` varchar(200) NOT NULL,
  `TYPE_ID` tinyint(2) NOT NULL,
  `STATUS_ID` tinyint(2) NOT NULL,
  `BUG_DONE` int(3) DEFAULT '0',
  `BUG_FROM` date NOT NULL,
  `BUG_TO` date NOT NULL,
  `BUG_Solution` varchar(1000) DEFAULT NULL,
  `BUG_DESCRIPTION` varchar(1000) DEFAULT NULL,
  `MEMBER_PROJECT_ID` int(4) NOT NULL,
  `CATEGORY_ID` tinyint(2) NOT NULL,
  `BUG_PRIORITY` varchar(20) NOT NULL,
  `PROJECT_ID` int(2) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- --------------------------------------------------------

--
-- Table structure for table `category`
--

CREATE TABLE `category` (
  `CATEGORY_ID` tinyint(2) NOT NULL,
  `CATEGORY_NAME` varchar(20) CHARACTER SET utf8 NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_bin;

--
-- Dumping data for table `category`
--

INSERT INTO `category` (`CATEGORY_ID`, `CATEGORY_NAME`) VALUES
(1, 'UI'),
(2, 'Design'),
(3, 'UT'),
(4, 'IT'),
(5, 'Logic'),
(6, 'Environment'),
(7, 'CR'),
(8, 'Other');

-- --------------------------------------------------------

--
-- Table structure for table `detail_timesheet`
--

CREATE TABLE `detail_timesheet` (
  `DETAIL_TIMESHEET_ID` int(6) NOT NULL,
  `DETAIL_TIMESHEET_DATE` date NOT NULL,
  `HOUR` decimal(2,1) NOT NULL,
  `PRE_DEFINED_ID` tinyint(2) NOT NULL,
  `PROCESS_ID` tinyint(2) NOT NULL,
  `TYPE_ID` tinyint(2) NOT NULL,
  `TASK_ID` int(6) DEFAULT NULL,
  `WORKCONTENT` varchar(500) CHARACTER SET utf8 DEFAULT NULL,
  `TS_ID` int(6) NOT NULL,
  `STATUS_ID` varchar(20) COLLATE utf8_bin NOT NULL DEFAULT 'Request'
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_bin;

-- --------------------------------------------------------

--
-- Table structure for table `member_project`
--

CREATE TABLE `member_project` (
  `MEMBER_PROJECT_ID` int(4) NOT NULL,
  `USER_ID` int(4) NOT NULL,
  `MEMBER_PROJECT_NAME` varchar(50) CHARACTER SET utf8 NOT NULL,
  `ROLE_ID` tinyint(2) NOT NULL,
  `MEMBER_PROJECT_EFFORT` int(3) NOT NULL DEFAULT '100',
  `PROJECT_ID` int(4) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_bin;

-- --------------------------------------------------------

--
-- Table structure for table `milestone_info`
--

CREATE TABLE `milestone_info` (
  `PROJECT_ID` int(4) NOT NULL,
  `MILESTONE_DATE` date NOT NULL,
  `MILESTONE_DESCRIPTION` varchar(1000) CHARACTER SET utf8 DEFAULT NULL,
  `MILESTONE_ID` int(4) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_bin;

-- --------------------------------------------------------

--
-- Table structure for table `permission`
--

CREATE TABLE `permission` (
  `per_id` int(11) NOT NULL,
  `over_view` tinyint(4) DEFAULT '0',
  `pro_detail` tinyint(4) DEFAULT '0',
  `add_pro` tinyint(4) DEFAULT '0',
  `set_upd` tinyint(4) DEFAULT '0',
  `set_res` tinyint(4) DEFAULT '0',
  `cre_iss` tinyint(4) DEFAULT '0',
  `upd_iss` tinyint(4) DEFAULT '0',
  `set_reg` tinyint(4) DEFAULT '0',
  `eff_mana` tinyint(4) DEFAULT '0',
  `eff_can` tinyint(4) DEFAULT '0',
  `qva_upd` tinyint(4) DEFAULT '0',
  `tms_re` tinyint(4) DEFAULT '0',
  `user_reg` tinyint(4) DEFAULT '0',
  `iss_list` tinyint(4) DEFAULT '0',
  `tms_list` tinyint(4) DEFAULT '0',
  `qva_list` tinyint(4) DEFAULT '0',
  `ROLE_ID` int(11) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_bin;

--
-- Dumping data for table `permission`
--

INSERT INTO `permission` (`per_id`, `over_view`, `pro_detail`, `add_pro`, `set_upd`, `set_res`, `cre_iss`, `upd_iss`, `set_reg`, `eff_mana`, `eff_can`, `qva_upd`, `tms_re`, `user_reg`, `iss_list`, `tms_list`, `qva_list`, `ROLE_ID`) VALUES
(1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 2),
(2, 1, 1, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 3),
(3, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 4),
(4, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 5),
(5, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 6);

-- --------------------------------------------------------

--
-- Table structure for table `pre_defined`
--

CREATE TABLE `pre_defined` (
  `PRE_DEFINED_ID` tinyint(2) NOT NULL,
  `PRE_DEFINED_NAME` varchar(50) CHARACTER SET utf8 NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_bin;

--
-- Dumping data for table `pre_defined`
--

INSERT INTO `pre_defined` (`PRE_DEFINED_ID`, `PRE_DEFINED_NAME`) VALUES
(1, 'Coding'),
(2, 'Fixbug'),
(3, 'Code Review'),
(4, 'Create UT'),
(5, 'Execute UT'),
(6, 'Meeting');

-- --------------------------------------------------------

--
-- Table structure for table `process`
--

CREATE TABLE `process` (
  `PROCESS_ID` tinyint(2) NOT NULL,
  `PROCESS_NAME` varchar(200) CHARACTER SET utf8 NOT NULL,
  `PROCESS_TYPE` varchar(50) CHARACTER SET utf8 NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_bin;

--
-- Dumping data for table `process`
--

INSERT INTO `process` (`PROCESS_ID`, `PROCESS_NAME`, `PROCESS_TYPE`) VALUES
(1, 'Requirement', 'Process'),
(2, 'Design', 'Process'),
(3, 'Coding', 'Process'),
(4, 'Test', 'Process'),
(5, 'Project Management', 'Process');

-- --------------------------------------------------------

--
-- Table structure for table `project_info`
--

CREATE TABLE `project_info` (
  `PROJECT_ID` int(4) NOT NULL,
  `PROJECT_NAME` varchar(50) CHARACTER SET utf8 NOT NULL,
  `PROJECT_FROM` date NOT NULL,
  `PROJECT_TO` date NOT NULL,
  `PROJECT_CHARGE_COST` int(3) NOT NULL,
  `STATUS_ID` tinyint(2) NOT NULL,
  `TYPE_ID` tinyint(2) NOT NULL,
  `PROJECT_DESCRIPTION` varchar(1000) CHARACTER SET utf8 DEFAULT NULL,
  `PROJECT_TECHNICAL` varchar(100) CHARACTER SET utf8 NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_bin;

-- --------------------------------------------------------

--
-- Table structure for table `role`
--

CREATE TABLE `role` (
  `ROLE_ID` tinyint(2) NOT NULL,
  `ROLE_NAME` varchar(50) CHARACTER SET utf8 NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_bin;

--
-- Dumping data for table `role`
--

INSERT INTO `role` (`ROLE_ID`, `ROLE_NAME`) VALUES
(1, 'Admin'),
(2, 'Manager'),
(3, 'Developer'),
(4, 'Tester'),
(5, 'Reporter'),
(6, 'Custormer');

-- --------------------------------------------------------

--
-- Table structure for table `scope_info`
--

CREATE TABLE `scope_info` (
  `SCOPE_ID` tinyint(2) NOT NULL,
  `SCOPE_NAME` varchar(20) CHARACTER SET utf8 NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_bin;

--
-- Dumping data for table `scope_info`
--

INSERT INTO `scope_info` (`SCOPE_ID`, `SCOPE_NAME`) VALUES
(1, 'BD'),
(2, 'DD'),
(3, 'Coding'),
(4, 'UT'),
(5, 'IT'),
(6, 'ST');

-- --------------------------------------------------------

--
-- Table structure for table `scope_project`
--

CREATE TABLE `scope_project` (
  `SCOPE_PROECT_ID` int(3) NOT NULL,
  `PROJECT_ID` int(4) NOT NULL,
  `SCOPE_ID` tinyint(2) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_bin;

-- --------------------------------------------------------

--
-- Table structure for table `status_info`
--

CREATE TABLE `status_info` (
  `STATUS_ID` tinyint(2) NOT NULL,
  `STATUS_TYPE` varchar(20) CHARACTER SET utf8 NOT NULL,
  `STATUS_NAME` varchar(30) CHARACTER SET utf8 NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_bin;

--
-- Dumping data for table `status_info`
--

INSERT INTO `status_info` (`STATUS_ID`, `STATUS_TYPE`, `STATUS_NAME`) VALUES
(1, 'Open', 'project'),
(2, 'Close', 'project'),
(3, 'Pending', 'project'),
(4, 'Open', 'task'),
(5, 'On-going', 'task'),
(6, 'Close', 'task'),
(7, 'Cancel', 'task'),
(8, 'Open', 'QA'),
(9, 'External', 'QA'),
(10, 'Answer', 'QA'),
(11, 'Close', 'QA'),
(12, 'Cancel', 'QA'),
(13, 'Request', 'timesheet'),
(14, 'Approved', 'timesheet'),
(15, 'Reject', 'timesheet');

-- --------------------------------------------------------

--
-- Table structure for table `task_info`
--

CREATE TABLE `task_info` (
  `TASK_ID` int(6) NOT NULL,
  `TASK_SUBJECT` varchar(200) CHARACTER SET utf8 NOT NULL,
  `TYPE_ID` tinyint(2) NOT NULL,
  `STATUS_ID` tinyint(2) NOT NULL,
  `TASK_DONE` int(3) DEFAULT NULL,
  `TASK_FROM` date NOT NULL,
  `TASK_TO` date NOT NULL,
  `TASK_Solution` varchar(1000) CHARACTER SET utf8 DEFAULT NULL,
  `TASK_DESCRIPTION` varchar(1000) CHARACTER SET utf8 DEFAULT NULL,
  `MEMBER_PROJECT_ID` int(4) NOT NULL,
  `CATEGORY_ID` tinyint(2) NOT NULL,
  `TASK_PRIORITY` varchar(20) CHARACTER SET utf8 NOT NULL DEFAULT 'medium',
  `PROJECT_ID` int(2) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_bin;

-- --------------------------------------------------------

--
-- Table structure for table `timesheet_info`
--

CREATE TABLE `timesheet_info` (
  `TS_ID` int(6) NOT NULL,
  `PROJECT_ID` int(4) NOT NULL,
  `MEMBER_PROECT_ID` int(4) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_bin;

-- --------------------------------------------------------

--
-- Table structure for table `type`
--

CREATE TABLE `type` (
  `TYPE_ID` tinyint(2) NOT NULL,
  `TYPE_NAME` varchar(30) CHARACTER SET utf8mb4 NOT NULL,
  `TYPE_TYPE` varchar(20) CHARACTER SET utf8mb4 NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_bin;

--
-- Dumping data for table `type`
--

INSERT INTO `type` (`TYPE_ID`, `TYPE_NAME`, `TYPE_TYPE`) VALUES
(1, 'Development', 'project'),
(2, 'Maintain', 'project'),
(3, 'Spec', 'task'),
(4, 'Task', 'task'),
(5, 'Bug', 'bug'),
(6, 'Issue', 'task'),
(7, 'Study', 'timesheet'),
(8, 'Create', 'timesheet'),
(9, 'Correct', 'timesheet'),
(10, 'Review', 'timesheet');

-- --------------------------------------------------------

--
-- Table structure for table `user_info`
--

CREATE TABLE `user_info` (
  `USER_ID` int(4) NOT NULL,
  `USER_FULLNAME` varchar(50) CHARACTER SET utf8 NOT NULL,
  `USER_MAIL` varchar(50) CHARACTER SET utf8 NOT NULL,
  `USER_PASSWORD` varchar(100) CHARACTER SET utf8 NOT NULL,
  `ROLE_ID` tinyint(2) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_bin;

--
-- Dumping data for table `user_info`
--

INSERT INTO `user_info` (`USER_ID`, `USER_FULLNAME`, `USER_MAIL`, `USER_PASSWORD`, `ROLE_ID`) VALUES
(1, '1', '1', '1', 1),
(2, 'Mạnh', 'Manhnv@tinhvan.com', '1', 3),
(3, 'Đại', 'Dai@tinhvan.com', '1', 3),
(4, 'Sơn', 'Son@tinhvan.com', '1', 3),
(5, 'Lượng', 'Luong@tinhvan.com', '1', 4),
(6, 'Thắng', 'ThangNN@tinhvan.com', '1', 2);

--
-- Indexes for dumped tables
--

--
-- Indexes for table `anser_question`
--
ALTER TABLE `anser_question`
  ADD PRIMARY KEY (`Q_A_ID`,`PROJECT_ID`);

--
-- Indexes for table `bug_info`
--
ALTER TABLE `bug_info`
  ADD PRIMARY KEY (`BUG_ID`,`PROJECT_ID`);

--
-- Indexes for table `category`
--
ALTER TABLE `category`
  ADD PRIMARY KEY (`CATEGORY_ID`);

--
-- Indexes for table `detail_timesheet`
--
ALTER TABLE `detail_timesheet`
  ADD PRIMARY KEY (`DETAIL_TIMESHEET_ID`,`TS_ID`);

--
-- Indexes for table `member_project`
--
ALTER TABLE `member_project`
  ADD PRIMARY KEY (`MEMBER_PROJECT_ID`,`USER_ID`,`PROJECT_ID`);

--
-- Indexes for table `milestone_info`
--
ALTER TABLE `milestone_info`
  ADD PRIMARY KEY (`MILESTONE_ID`) USING BTREE;

--
-- Indexes for table `permission`
--
ALTER TABLE `permission`
  ADD PRIMARY KEY (`per_id`);

--
-- Indexes for table `pre_defined`
--
ALTER TABLE `pre_defined`
  ADD PRIMARY KEY (`PRE_DEFINED_ID`);

--
-- Indexes for table `process`
--
ALTER TABLE `process`
  ADD PRIMARY KEY (`PROCESS_ID`);

--
-- Indexes for table `project_info`
--
ALTER TABLE `project_info`
  ADD PRIMARY KEY (`PROJECT_ID`);

--
-- Indexes for table `role`
--
ALTER TABLE `role`
  ADD PRIMARY KEY (`ROLE_ID`);

--
-- Indexes for table `scope_info`
--
ALTER TABLE `scope_info`
  ADD PRIMARY KEY (`SCOPE_ID`);

--
-- Indexes for table `scope_project`
--
ALTER TABLE `scope_project`
  ADD PRIMARY KEY (`SCOPE_PROECT_ID`,`PROJECT_ID`,`SCOPE_ID`),
  ADD KEY `FK_ID_idx` (`SCOPE_ID`);

--
-- Indexes for table `status_info`
--
ALTER TABLE `status_info`
  ADD PRIMARY KEY (`STATUS_ID`);

--
-- Indexes for table `task_info`
--
ALTER TABLE `task_info`
  ADD PRIMARY KEY (`TASK_ID`,`PROJECT_ID`);

--
-- Indexes for table `timesheet_info`
--
ALTER TABLE `timesheet_info`
  ADD PRIMARY KEY (`TS_ID`,`PROJECT_ID`,`MEMBER_PROECT_ID`);

--
-- Indexes for table `type`
--
ALTER TABLE `type`
  ADD PRIMARY KEY (`TYPE_ID`);

--
-- Indexes for table `user_info`
--
ALTER TABLE `user_info`
  ADD PRIMARY KEY (`USER_ID`);

--
-- AUTO_INCREMENT for dumped tables
--

--
-- AUTO_INCREMENT for table `anser_question`
--
ALTER TABLE `anser_question`
  MODIFY `Q_A_ID` int(4) NOT NULL AUTO_INCREMENT;
--
-- AUTO_INCREMENT for table `bug_info`
--
ALTER TABLE `bug_info`
  MODIFY `BUG_ID` int(6) NOT NULL AUTO_INCREMENT;
--
-- AUTO_INCREMENT for table `category`
--
ALTER TABLE `category`
  MODIFY `CATEGORY_ID` tinyint(2) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=9;
--
-- AUTO_INCREMENT for table `detail_timesheet`
--
ALTER TABLE `detail_timesheet`
  MODIFY `DETAIL_TIMESHEET_ID` int(6) NOT NULL AUTO_INCREMENT;
--
-- AUTO_INCREMENT for table `member_project`
--
ALTER TABLE `member_project`
  MODIFY `MEMBER_PROJECT_ID` int(4) NOT NULL AUTO_INCREMENT;
--
-- AUTO_INCREMENT for table `milestone_info`
--
ALTER TABLE `milestone_info`
  MODIFY `MILESTONE_ID` int(4) NOT NULL AUTO_INCREMENT;
--
-- AUTO_INCREMENT for table `permission`
--
ALTER TABLE `permission`
  MODIFY `per_id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=6;
--
-- AUTO_INCREMENT for table `pre_defined`
--
ALTER TABLE `pre_defined`
  MODIFY `PRE_DEFINED_ID` tinyint(2) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=7;
--
-- AUTO_INCREMENT for table `process`
--
ALTER TABLE `process`
  MODIFY `PROCESS_ID` tinyint(2) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=6;
--
-- AUTO_INCREMENT for table `project_info`
--
ALTER TABLE `project_info`
  MODIFY `PROJECT_ID` int(4) NOT NULL AUTO_INCREMENT;
--
-- AUTO_INCREMENT for table `role`
--
ALTER TABLE `role`
  MODIFY `ROLE_ID` tinyint(2) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=7;
--
-- AUTO_INCREMENT for table `scope_info`
--
ALTER TABLE `scope_info`
  MODIFY `SCOPE_ID` tinyint(2) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=7;
--
-- AUTO_INCREMENT for table `scope_project`
--
ALTER TABLE `scope_project`
  MODIFY `SCOPE_PROECT_ID` int(3) NOT NULL AUTO_INCREMENT;
--
-- AUTO_INCREMENT for table `status_info`
--
ALTER TABLE `status_info`
  MODIFY `STATUS_ID` tinyint(2) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=16;
--
-- AUTO_INCREMENT for table `task_info`
--
ALTER TABLE `task_info`
  MODIFY `TASK_ID` int(6) NOT NULL AUTO_INCREMENT;
--
-- AUTO_INCREMENT for table `timesheet_info`
--
ALTER TABLE `timesheet_info`
  MODIFY `TS_ID` int(6) NOT NULL AUTO_INCREMENT;
--
-- AUTO_INCREMENT for table `type`
--
ALTER TABLE `type`
  MODIFY `TYPE_ID` tinyint(2) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=11;
--
-- AUTO_INCREMENT for table `user_info`
--
ALTER TABLE `user_info`
  MODIFY `USER_ID` int(4) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=7;COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
