# /bin/env bash

cd backend
./gradlew openApiGenerate

cd ../front-medico
npm run openapi:generate

cd ../front-paciente
npm run openapi:generate