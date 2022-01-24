SELECT id, name, avg_age, discovery_date FROM public.fauna WHERE name LIKE '%fish%' ;
SELECT id, name, avg_age, discovery_date FROM public.fauna WHERE avg_age>10000 and avg_age<21000 ;
SELECT id, name, avg_age, discovery_date FROM public.fauna WHERE discovery_date is null;
SELECT id, name, avg_age, discovery_date FROM public.fauna WHERE discovery_date < '1950-01-01';