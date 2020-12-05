select id, name from student where id in (
    select distinct student_no from score_relation where course_no in (
        select course_no from score_relation where student_no = 7
    )
) order by id asc


