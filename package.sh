mvn package -Dmaven.test.skip=true

cd bae/lingxi
git add -A
git commit -m publish
git push

bae app publish

stat root.war

cd ../../