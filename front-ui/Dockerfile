FROM node:18 as build-stage

WORKDIR /app

COPY package*.json ./

RUN npm install

COPY . .

RUN npm run build

FROM nginx:alpine

#COPY ../front-ui/nginx.conf /etc/nginx/nginx.conf

COPY --from=build-stage /app/dist/front-ui/browser /usr/share/nginx/html

EXPOSE 80

# Start nginx
CMD ["nginx", "-g", "daemon off;"]