"use client";

import ChatInterface from "@/components/ChatInterface";

import React, { use } from "react";

interface ChatPageProps {
  params: {
    uuid: string;
  };
}

const ChatPage = ({ params }: ChatPageProps) => {
  const { uuid } = use(params);

  return <ChatInterface uuid={uuid} />;
};

export default ChatPage;
