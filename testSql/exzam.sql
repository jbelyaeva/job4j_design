<!--название компании для каждого человека -->
select
p.name,
c.name
from person p
left join company c
on p.company_id=c.id;

 <!--все person, кто не в компании с id=5-->
 select
 p.name
 from person p, company c
 where
 p.company_id=c.id
 and c.id!=5;

 <!--Название компании с максимальным количеством человек + количество человек в этой компании-->
<!--(нужно учесть, что таких компаний может быть несколько).-->

select m.*
from (select
        c.name as nameComp,
        count(p.*) as countInt
        from person as p,
             company as c
        where p.company_id=c.id
        group by c.name
	    ) as m
where m.countInt=(
select
    max(maxresult.countInt) as maxcountInt
    from(
        select
        c.name as nameComp,
        count(p.*) as countInt
        from person as p,
             company as c
        where p.company_id=c.id
        group by c.name
	    ) as maxresult
	)